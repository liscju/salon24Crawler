package pl.edu.agh.agents.crawler.internal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SiteDownloader {
    static final int COUNT_OF_RECCONNECT_AT_PROBLEM_WITH_LINK = 5;

    private SiteDownloader() {
    }

    static Document getDocument(String link) throws IOException {
        for (int i = 0; i < COUNT_OF_RECCONNECT_AT_PROBLEM_WITH_LINK - 1; i++) {
            try {
                Document document = Jsoup.connect(link).get();
                return document;
            } catch (IOException e) {
                // Do nothing,we will try again
            }
        }
        return Jsoup.connect(link).get(); // Return or Throw :P
    }
}