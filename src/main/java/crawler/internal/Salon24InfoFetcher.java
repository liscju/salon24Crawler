package crawler.internal;

import crawler.api.ArticleContent;
import crawler.internal.HTMLArticleContentExtractor;
import crawler.internal.HTMLListOfArticlesExtractor;
import crawler.internal.HTMLListOfSitesWithLinksToArticlesExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Salon24InfoFetcher {
    private static final int COUNT_OF_RECCONNECT_AT_PROBLEM_WITH_LINK = 5;

    public List<ArticleContent> fetchArticles() throws IOException {
        List<ArticleContent> bloggerArticles = new LinkedList<ArticleContent>();

        Document iteratingDocumentWithLinksToArticles;
        while ( (iteratingDocumentWithLinksToArticles =
                    getDocumentsWithLinksToArticles().nextSiteWithLinksToArticles()) != null) {

            List<String> documentLinks =
                    new HTMLListOfArticlesExtractor(iteratingDocumentWithLinksToArticles).extractLinks();

            for (String link : documentLinks) {
                Document linkDocumentArticle = getDocument(link);
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
        for (int i=0;i < COUNT_OF_RECCONNECT_AT_PROBLEM_WITH_LINK-1;i++) {
            try {
                Document document = Jsoup.connect(link).get();
                return document;
            } catch (IOException e) {
                // Do nothing,we will try again
            }
        }
        return Jsoup.connect(link).get();
    }

    protected abstract Date getSince();

    protected abstract HTMLListOfSitesWithLinksToArticlesExtractor getDocumentsWithLinksToArticles();
}
