package crawler;


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

/**
 * Given main link of blogger in salon24 return all his articles
 * Example Input:
 * http://korwin-mikke.salon24.pl/ or http://dolinanicosci.salon24.pl/
 */
public class Salon24BloggerInfoFetcher {
    private String url;
    private Date since;

    public Salon24BloggerInfoFetcher(String url) {
        this.url = url;
        this.since = new Date(Long.MIN_VALUE); // minimum date val,any other date will be after this :P
    }

    public Salon24BloggerInfoFetcher(String url,Date since) {
        this.url = url;
        this.since = since;
    }

    public List<ArticleContent> fetchArticles() throws IOException {
        List<ArticleContent> bloggerArticles = new LinkedList<ArticleContent>();
        HTMLListOfSitesWithLinksToArticlesExtractor documentsWithLinksToArticles =
                new HTMLListOfSitesWithLinksToArticlesExtractor(url);

        Document iteratingDocumentWithLinksToArticles;
        while ( (iteratingDocumentWithLinksToArticles =
                    documentsWithLinksToArticles.nextSiteWithLinksToArticles()) != null) {

            List<String> documentLinks =
                    new HTMLListOfArticlesExtractor(iteratingDocumentWithLinksToArticles).extractLinks();

            for (String link : documentLinks) {
                Document linkDocumentArticle = Jsoup.connect(link).get();
                ArticleContent articleContent = new HTMLArticleContentExtractor(linkDocumentArticle).extractContent();
                if (articleContent.getCreated().after(since)) {
                    bloggerArticles.add(articleContent);
                } else {
                    return bloggerArticles; // ooops, im out
                }
            }
        }
        return bloggerArticles;
    }
}















