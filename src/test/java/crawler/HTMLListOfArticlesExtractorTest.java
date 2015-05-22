package crawler;

import crawler.internal.HTMLListOfArticlesExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

@Ignore
public class HTMLListOfArticlesExtractorTest {
    @Test
    public void extractTestCase1() throws Exception {
        String url = "http://korwin-mikke.salon24.pl/";
        Document document = Jsoup.connect(url).get();
        HTMLListOfArticlesExtractor linksExtractor = new HTMLListOfArticlesExtractor(document);
        List<String> links = linksExtractor.extractLinks();
        System.out.println(links);
    }

}