package crawler;


import crawler.internal.HTMLBlogListOfSitesWithLinksToArticlesExtractor;
import crawler.internal.HTMLListOfSitesWithLinksToArticlesExtractor;
import crawler.internal.HTMLNewsListOfSitesWithLinksToArticlesExtractor;
import crawler.internal.Salon24InfoFetcher;

import java.util.Date;

/**
 * Given main link of blogger in salon24 return all his articles
 * Example Input:
 * http://korwin-mikke.salon24.pl/ or http://dolinanicosci.salon24.pl/
 */
public class Salon24NewsInfoFetcher extends Salon24InfoFetcher {
    private Date since;
    private HTMLListOfSitesWithLinksToArticlesExtractor documentsWithLinksToArticles;

    public Salon24NewsInfoFetcher() {
        this.since = new Date(Long.MIN_VALUE); // minimum date val,any other date will be after this :P
        documentsWithLinksToArticles = new HTMLNewsListOfSitesWithLinksToArticlesExtractor();
    }

    public Salon24NewsInfoFetcher(Date since) {
        this.since = since;
        documentsWithLinksToArticles = new HTMLNewsListOfSitesWithLinksToArticlesExtractor();
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















