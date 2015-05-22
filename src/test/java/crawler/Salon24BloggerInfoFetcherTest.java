package crawler;

import crawler.api.ArticleContent;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class Salon24BloggerInfoFetcherTest {

    @Test
    public void testFetchArticles() throws Exception {
        Salon24BloggerInfoFetcher salon24BloggerInfoFetcher =
                new Salon24BloggerInfoFetcher("http://korwin-mikke.salon24.pl/",new Date(2015,4,1));
        List<ArticleContent> articleContents = salon24BloggerInfoFetcher.fetchArticles();
        System.out.println(articleContents);
    }
}