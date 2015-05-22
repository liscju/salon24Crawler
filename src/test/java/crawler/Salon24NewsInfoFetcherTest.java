package crawler;

import crawler.api.ArticleContent;
import crawler.internal.HTMLListOfSitesWithLinksToArticlesExtractor;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class Salon24NewsInfoFetcherTest {

    @Test
    public void testGetDocumentsWithLinksToArticles() throws Exception {
        Salon24NewsInfoFetcher salon24NewsInfoFetcher =
                new Salon24NewsInfoFetcher(new Date(2015,4,21) );

        List<ArticleContent> articleContents = salon24NewsInfoFetcher.fetchArticles();
        System.out.println(articleContents);
    }

    @Test
    public void testExample() throws Exception {
        Jsoup.connect("http://rogerus.dlaczego.salon24.pl/649659,duda-wygral").get();
    }
}