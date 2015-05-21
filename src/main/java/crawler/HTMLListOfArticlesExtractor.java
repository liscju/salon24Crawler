package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * Given site with list of articles returns list
 * of links to articles
 * Example:
 * Given
 *    http://korwin-mikke.salon24.pl/posts/0,2,wszystkie
 * Returns links to:
 *    Link to "A tak było w Gdańsku"
 *    Link to "Polska: dumna, bogata,..."
 *    ...
 *    Link to "Chyba 75% przedsiebiorcow..."
 */
public class HTMLListOfArticlesExtractor {
    private final Document document;

    public HTMLListOfArticlesExtractor(Document document) {
        this.document = document;
    }

    public List<String> extractLinks() {
        Elements posts = document.select("div#content div.content-left ul.post-list > li");
        List<String> links = new LinkedList<String>();
        for (Element post : posts) {
            String link = extractLink(post);
            links.add(link);
        }
        return links;
    }

    private String extractLink(Element post) {
        Element linkElement = post.select("h2 > a").first();
        String link = linkElement.attr("href");
        return link;
    }
}





















