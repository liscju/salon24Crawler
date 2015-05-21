package crawler;

import crawler.api.ArticleContent;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLArticleContentExtractor {
    private final Document document;

    public HTMLArticleContentExtractor(Document document) {
        this.document = document;
    }

    public ArticleContent extractContent() {
        Elements articleHeader = document.select("article.post > header").select("h1");
        String title = articleHeader.text();
        Elements articleBody = document.select("article.post > div.article-body");
        String content = articleBody.text();
        return new ArticleContent(title,content,null);
    }
}
