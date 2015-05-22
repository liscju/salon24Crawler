package crawler;


import crawler.api.ArticleContent;
import crawler.internal.HTMLArticleContentExtractor;
import crawler.internal.HTMLListOfArticlesExtractor;
import crawler.internal.HTMLListOfSitesWithLinksToArticlesExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Given main link of blogger in salon24 return all his articles
 * Example Input:
 * http://korwin-mikke.salon24.pl/ or http://dolinanicosci.salon24.pl/
 */
public class Salon24BloggerInfoFetcher {
    private String url;

    public Salon24BloggerInfoFetcher(String url) {
        this.url = url;
    }

    public List<ArticleContent> fetchArticles() throws IOException {
        List<ArticleContent> bloggerArticles = new LinkedList<ArticleContent>();
        List<Document> documentsWithLinksToArticles =
                new HTMLListOfSitesWithLinksToArticlesExtractor(url).extractSitesWithLinksToArticles();

        for (Document document : documentsWithLinksToArticles) {
            List<String> documentLinks = new HTMLListOfArticlesExtractor(document).extractLinks();

            for (String link : documentLinks) {
                Document linkDocumentArticle = Jsoup.connect(link).get();
                ArticleContent articleContent = new HTMLArticleContentExtractor(linkDocumentArticle).extractContent();
                bloggerArticles.add(articleContent);
            }
        }
        return bloggerArticles;
    }
}















