package crawler.internal;

import crawler.api.ArticleContent;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Salon24InfoFetcher {

    public List<ArticleContent> fetchArticles(){
        List<ArticleContent> bloggerArticles = new LinkedList<ArticleContent>();

        Document iteratingDocumentWithLinksToArticles;
        try {
            while ( (iteratingDocumentWithLinksToArticles =
                        getDocumentsWithLinksToArticles().nextSiteWithLinksToArticles()) != null) {

                List<String> documentLinks =
                        null;
                try {
                    documentLinks = new HTMLListOfArticlesExtractor(iteratingDocumentWithLinksToArticles).extractLinks();
                } catch (Exception e) {
                    Logger.logException("Salon24InfoFetcher.fetchArticles exception while extracting links", e);
                    documentLinks = new LinkedList<String>();
                }

                for (String link : documentLinks) {
                    try {
                        Document linkDocumentArticle = SiteDownloader.getDocument(link);
                        ArticleContent articleContent = new HTMLArticleContentExtractor(linkDocumentArticle).extractContent();
                        if (articleContent.getCreated().after(getSince())) {
                            bloggerArticles.add(articleContent);
                        } else {
                            return bloggerArticles; // ooops, im out
                        }
                    } catch (IOException e) {
                        Logger.logException("Salon24InfoFetcher.fetchArticles exception while extracting article content", e);
                    }
                }
            }
        } catch (IOException e) {
            Logger.logException("Salon24InfoFetcher.fetchArticles cant get NextSiteWithLinksToArticles", e);
        }
        return bloggerArticles;
    }

    protected abstract Date getSince();

    protected abstract HTMLListOfSitesWithLinksToArticlesExtractor getDocumentsWithLinksToArticles();
}
