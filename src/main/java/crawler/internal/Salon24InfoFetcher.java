package crawler.internal;

import crawler.api.ArticleContent;
import dao.NewsContentDAO;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Salon24InfoFetcher {

    public void fetchArticles(){
        Document iteratingDocumentWithLinksToArticles;
        try {
            NewsContentDAO newsContentDAO = NewsContentDAO.getInstance();
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
                        ArticleContent articleContent = new HTMLArticleContentExtractor(link,linkDocumentArticle).extractContent();
                        if (articleContent.getCreated().after(getSince())) {
                            newsContentDAO.saveArticleContent(articleContent);
                        } else {
                            return; // ooops, im out
                        }
                    } catch (IOException e) {
                        Logger.logException("Salon24InfoFetcher.fetchArticles exception while extracting article content", e);
                    }
                }
            }
        } catch (IOException e) {
            Logger.logException("Salon24InfoFetcher.fetchArticles cant get NextSiteWithLinksToArticles", e);
        }
    }

    protected abstract Date getSince();

    protected abstract HTMLListOfSitesWithLinksToArticlesExtractor getDocumentsWithLinksToArticles();
}
