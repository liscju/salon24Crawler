package crawler;

import crawler.api.ArticleContent;
import org.hamcrest.CoreMatchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

@Ignore
public class HTMLArticleContentExtractorTest {

    private void downloadAndCheckExtractor(String url, String expectedTitle, String expectedArticlePreffix, String expectedArticleSuffix) throws IOException {
        Document document = Jsoup.connect(url).get();
        HTMLArticleContentExtractor contentExtractor = new HTMLArticleContentExtractor(document);
        ArticleContent articleContent = contentExtractor.extractContent();
        assertEquals(expectedTitle, articleContent.getTitle());
        assertThat(articleContent.getMainContent(), CoreMatchers.startsWith(expectedArticlePreffix));
        assertThat(articleContent.getMainContent(), CoreMatchers.endsWith(expectedArticleSuffix));
    }

    @Test
    public void extractTestCase1() throws Exception {
        String url = "http://tomaszsakiewicz.salon24.pl/648754,za-mundurem-hrabia-z-bulem";
        String expectedTitle = "Za mundurem hrabia z bulem";
        String expectedArticlePreffix = "Kto stoi za Andrzejem";
        String expectedArticleSuffix = "20/2015 z dn. 20.05.2015r.";

        downloadAndCheckExtractor(url, expectedTitle, expectedArticlePreffix, expectedArticleSuffix);
    }

    @Test
    public void extractTestCase2() throws Exception {
        String url = "http://korwin-mikke.salon24.pl/649099,problem-imigrantow";
        String expectedTitle = "\"Problem” imigrantów";
        String expectedArticlePreffix = " W ub. tygodniu";
        String expectedArticleSuffix = "a potem won!";

        downloadAndCheckExtractor(url, expectedTitle, expectedArticlePreffix, expectedArticleSuffix);
    }

    @Test
    public void extractTestCase3() throws Exception {
        String url = "http://korwin-mikke.salon24.pl/613347,przychodze-tu-z-onetu";
        Document document = Jsoup.connect(url).get();
        HTMLArticleContentExtractor contentExtractor = new HTMLArticleContentExtractor(document);
        ArticleContent articleContent = contentExtractor.extractContent();
    }

}