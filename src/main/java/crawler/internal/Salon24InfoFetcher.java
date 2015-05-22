package crawler.internal;

import crawler.api.ArticleContent;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Salon24InfoFetcher {

    public List<ArticleContent> fetchArticles() throws IOException {
        List<ArticleContent> bloggerArticles = new LinkedList<ArticleContent>();

        Document iteratingDocumentWithLinksToArticles;
        while ( (iteratingDocumentWithLinksToArticles =
                    getDocumentsWithLinksToArticles().nextSiteWithLinksToArticles()) != null) {

            List<String> documentLinks =
                    new HTMLListOfArticlesExtractor(iteratingDocumentWithLinksToArticles).extractLinks();

            for (String link : documentLinks) {
                Document linkDocumentArticle = SiteDownloader.getDocument(link);
                ArticleContent articleContent = new HTMLArticleContentExtractor(linkDocumentArticle).extractContent();
                if (articleContent.getCreated().after(getSince())) {
                    bloggerArticles.add(articleContent);
                } else {
                    return bloggerArticles; // ooops, im out
                }
            }
        }
        return bloggerArticles;
    }

    private Document getDocument(String link) throws IOException {
        return SiteDownloader.getDocument(link);
    }

    protected abstract Date getSince();

    protected abstract HTMLListOfSitesWithLinksToArticlesExtractor getDocumentsWithLinksToArticles();
}
