package crawler;

import crawler.api.ArticleContent;
import crawler.api.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

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

        List<Comment> comments = new LinkedList<Comment>();
        Elements articleComments = document.select("section#komentarze > ul.comments-list > li");
        for (Element articleComment : articleComments) {
            comments.add( extractComment(articleComment) );
        }
        return new ArticleContent(title,content,comments);
    }

    private Comment extractComment(Element articleComment) {
        Elements articleCommentTitle = articleComment.select("h3");
        Elements articleCommentBody = articleComment.select("div.comment-body");

        String commentTitle = articleCommentTitle.text();
        String commentBody = articleCommentBody.text();
        return new Comment(commentTitle,commentBody);
    }
}
