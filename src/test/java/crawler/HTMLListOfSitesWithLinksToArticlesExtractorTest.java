package crawler;

import crawler.internal.HTMLListOfSitesWithLinksToArticlesExtractor;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.List;

public class HTMLListOfSitesWithLinksToArticlesExtractorTest {
    @Test
    public void extractTestCase1() throws Exception {
        String url = "http://dolinanicosci.salon24.pl";
        HTMLListOfSitesWithLinksToArticlesExtractor linksExtractor = new HTMLListOfSitesWithLinksToArticlesExtractor(url);
        List<Document> documents = linksExtractor.extractSitesWithLinksToArticles();
        System.out.println(documents);
    }
}