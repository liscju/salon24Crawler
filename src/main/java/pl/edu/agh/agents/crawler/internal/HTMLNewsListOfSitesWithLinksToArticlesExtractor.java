package pl.edu.agh.agents.crawler.internal;

public class HTMLNewsListOfSitesWithLinksToArticlesExtractor extends HTMLListOfSitesWithLinksToArticlesExtractor{

    public HTMLNewsListOfSitesWithLinksToArticlesExtractor() {
        super("http://www.salon24.pl/kategoria");
    }

    @Override
    public String calculateLink(int i) {
        return mainUrl + "/0,wszystkie," + i;
    }
}
