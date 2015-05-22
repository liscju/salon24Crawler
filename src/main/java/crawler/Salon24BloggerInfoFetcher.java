package crawler;


import crawler.internal.*;

import java.util.Date;

/**
 * Given main link of blogger in salon24 return all his articles
 * Example Input:
 * http://korwin-mikke.salon24.pl/ or http://dolinanicosci.salon24.pl/
 */
public class Salon24BloggerInfoFetcher extends Salon24InfoFetcher {
    private String url;
    private Date since;
    private HTMLListOfSitesWithLinksToArticlesExtractor documentsWithLinksToArticles;

    public Salon24BloggerInfoFetcher(String url) {
        this.url = url;
        this.since = new Date(Long.MIN_VALUE); // minimum date val,any other date will be after this :P
        documentsWithLinksToArticles = new HTMLBlogListOfSitesWithLinksToArticlesExtractor(url);
    }

    public Salon24BloggerInfoFetcher(String url,Date since) {
        this.url = url;
        this.since = since;
        documentsWithLinksToArticles = new HTMLBlogListOfSitesWithLinksToArticlesExtractor(url);
    }

    @Override
    protected Date getSince() {
        return since;
    }

    @Override
    protected HTMLListOfSitesWithLinksToArticlesExtractor getDocumentsWithLinksToArticles() {
        return documentsWithLinksToArticles;
    }
}















