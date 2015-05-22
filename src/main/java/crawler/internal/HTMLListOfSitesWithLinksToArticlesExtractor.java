package crawler.internal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Extract information which sites of given blog contains
 * links of articles and returns them as org.jsoup.nodes.Document
 * Example:
 * Input: http://korwin-mikke.salon24.pl/
 * Output:
 *     Links:
 *     http://korwin-mikke.salon24.pl/
 *     http://korwin-mikke.salon24.pl/posts/0,1,wszystkie
 *     http://korwin-mikke.salon24.pl/posts/0,2,wszystkie
 *     ..............
 *     http://korwin-mikke.salon24.pl/posts/0,1,wszystkie
 *     http://korwin-mikke.salon24.pl/posts/0,6,wszystkie
 */
public class HTMLListOfSitesWithLinksToArticlesExtractor {
    private String mainUrl;
    private int currentDocument;
    private final LinkedList<Document> extractedDocuments;

    public HTMLListOfSitesWithLinksToArticlesExtractor(String mainUrl) {
        if (mainUrl.endsWith("/")) {
            this.mainUrl = mainUrl.substring(0, mainUrl.length() - 1);
        } else
            this.mainUrl = mainUrl;
        this.currentDocument = 1;
        this.extractedDocuments = new LinkedList<Document>();
    }

    public Document extractSitesWithLinksToArticles(int i) throws IOException {
        String currentLink = calculateLink(i);
        Document currentDocument = Jsoup.connect(currentLink).get();
        if (hasLinksToArticle(currentDocument)) {
            return currentDocument;
        } else {
            return null;
        }
    }

    public Document nextSiteWithLinksToArticles() throws IOException {
        return extractSitesWithLinksToArticles(this.currentDocument++);
    }

    public String calculateLink(int i) {
        return mainUrl + "/posts/0," + i + ",wszystkie";
    }

    public boolean hasLinksToArticle(Document document) {
        return document.select("div#content div.content-left ul.post-list > li").size() > 0;
    }
}













