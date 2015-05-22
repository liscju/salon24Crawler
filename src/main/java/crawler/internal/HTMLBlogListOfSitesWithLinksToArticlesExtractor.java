package crawler.internal;

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
public class HTMLBlogListOfSitesWithLinksToArticlesExtractor extends HTMLListOfSitesWithLinksToArticlesExtractor {

    public HTMLBlogListOfSitesWithLinksToArticlesExtractor(String mainUrl) {
        super(mainUrl);
    }

    @Override
    public String calculateLink(int i) {
        return mainUrl + "/posts/0," + i + ",wszystkie";
    }

}














