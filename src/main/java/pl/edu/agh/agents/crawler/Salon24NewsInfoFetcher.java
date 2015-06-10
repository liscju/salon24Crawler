package pl.edu.agh.agents.crawler;


import pl.edu.agh.agents.crawler.internal.HTMLListOfSitesWithLinksToArticlesExtractor;
import pl.edu.agh.agents.crawler.internal.HTMLNewsListOfSitesWithLinksToArticlesExtractor;
import pl.edu.agh.agents.crawler.internal.Salon24InfoFetcher;

import java.util.Date;

/**
 * Given main link of blogger in salon24 return all his articles
 * Example Input:
 * http://korwin-mikke.salon24.pl/ or http://dolinanicosci.salon24.pl/
 */
public class Salon24NewsInfoFetcher extends Salon24InfoFetcher {
    private Date since;
    private Date before;
    private HTMLListOfSitesWithLinksToArticlesExtractor documentsWithLinksToArticles;

    public Salon24NewsInfoFetcher() {
        this.since = new Date(Long.MIN_VALUE); // minimum date val,any other date will be after this :P
        this.before = new Date();
        documentsWithLinksToArticles = new HTMLNewsListOfSitesWithLinksToArticlesExtractor();
    }

    public Salon24NewsInfoFetcher(Date since,Date before) {
        this.since = since;
        this.before = before;
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

    @Override
    protected Date getBefore() {
        return before;
    }
}















