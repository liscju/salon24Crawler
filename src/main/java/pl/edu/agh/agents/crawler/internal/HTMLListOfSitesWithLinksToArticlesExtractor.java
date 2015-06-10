package pl.edu.agh.agents.crawler.internal;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedList;

public abstract class HTMLListOfSitesWithLinksToArticlesExtractor {
    protected final LinkedList<Document> extractedDocuments;
    protected String mainUrl;
    protected int currentDocument;

    public HTMLListOfSitesWithLinksToArticlesExtractor(String mainUrl) {
        this.extractedDocuments = new LinkedList<Document>();
        if (mainUrl.endsWith("/")) {
            this.mainUrl = mainUrl.substring(0, mainUrl.length() - 1);
        } else
            this.mainUrl = mainUrl;
        this.currentDocument = 1;
    }

    public Document extractSitesWithLinksToArticles(int i) throws IOException {
        String currentLink = calculateLink(i);
        Document currentDocument = SiteDownloader.getDocument(currentLink);
        if (hasLinksToArticle(currentDocument)) {
            return currentDocument;
        } else {
            return null;
        }
    }

    public Document nextSiteWithLinksToArticles() throws IOException {
        return extractSitesWithLinksToArticles(this.currentDocument++);
    }

    abstract String calculateLink(int i);

    public boolean hasLinksToArticle(Document document) {
        return document.select("div#content div.content-left ul.post-list > li").size() > 0;
    }
}
