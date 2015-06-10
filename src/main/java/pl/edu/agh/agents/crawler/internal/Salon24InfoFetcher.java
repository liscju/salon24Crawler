package pl.edu.agh.agents.crawler.internal;

import pl.edu.agh.agents.crawler.api.ArticleContent;
import pl.edu.agh.agents.dao.NewsContentDAO;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Salon24InfoFetcher {

    public void fetchArticles(){
        Document iteratingDocumentWithLinksToArticles;
        try {
            NewsContentDAO newsContentDAO = NewsContentDAO.getInstance();
            while ( (iteratingDocumentWithLinksToArticles =
                        getDocumentsWithLinksToArticles().nextSiteWithLinksToArticles()) != null) {

                List<String> documentLinks =
                        null;
                try {
                    documentLinks = new HTMLListOfArticlesExtractor(iteratingDocumentWithLinksToArticles).extractLinks();
                } catch (Exception e) {
                    Logger.logException("Salon24InfoFetcher.fetchArticles exception while extracting links", e);
                    documentLinks = new LinkedList<String>();
                }

                for (String link : documentLinks) {
                    try {
                        Document linkDocumentArticle = SiteDownloader.getDocument(link);
                        ArticleContent articleContent = new HTMLArticleContentExtractor(link, linkDocumentArticle).extractContent();
                        if (articleContent.getCreated().after(getSince())) {
                            if (articleContent.getCreated().before(getBefore())) {
                                newsContentDAO.saveArticleContent(articleContent);
                                printSavedArticle(articleContent);
                            }
                        } else {
                            return; // ooops, im out
                        }
                    } catch (IOException e) {
                        Logger.logException("Salon24InfoFetcher.fetchArticles io exception while extracting article content", e);
                    } catch (NullPointerException e) {
                        Logger.logException("Salon24InfoFetcher.fetchArticles nullpointerexception while extracting article content", e);
                    } catch (Throwable alle) {
                        Logger.logException("Salon24InfoFetcher.fetchArticles throwable excception while extracting article content",new Exception(alle));
                    }
                }
            }
        } catch (IOException e) {
            Logger.logException("Salon24InfoFetcher.fetchArticles cant get NextSiteWithLinksToArticles", e);
        }
    }

    private final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private void printSavedArticle(ArticleContent articleContent) {
        System.out.println("Saved Article with Date:" + df.format(articleContent.getCreated() ) );
    }

    protected abstract Date getSince();

    protected abstract HTMLListOfSitesWithLinksToArticlesExtractor getDocumentsWithLinksToArticles();

    protected abstract Date getBefore();
}
