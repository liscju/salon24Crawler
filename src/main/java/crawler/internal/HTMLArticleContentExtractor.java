package crawler.internal;

import crawler.api.ArticleContent;
import crawler.api.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract content of article from site given in document
 * which is one of salon24.pl blog
 * Examples of site with articles:
 * http://korwin-mikke.salon24.pl/613347,przychodze-tu-z-onetu
 * http://korwin-mikke.salon24.pl/649099,problem-imigrantow
 */
public class HTMLArticleContentExtractor {
    private final Document document;
    private final String link;

    public HTMLArticleContentExtractor(String link, Document document) {
        this.link = link;
        this.document = document;
    }

    public ArticleContent extractContent() {
        String author = extractArticleAuthor(document);
        Elements articleHeader = document.select("article.post > header").select("h1");
        String title = articleHeader.text();
        Elements articleCreated = document.select("article.post > header > span.created");
        Date created = extractArticleCreatedDate(articleCreated);
        Elements articleBody = document.select("article.post > div.article-body");
        String content = articleBody.text();

        List<Comment> comments = new LinkedList<Comment>();
        Elements articleComments = document.select("section#komentarze > ul.comments-list > li");
        for (Element articleComment : articleComments) {
            comments.add(
                    changeAuthorReceiverCommentToRealNick(
                            author,
                            extractComment(articleComment)
                    )
            );
        }
        return new ArticleContent(link,title,author,created,content,comments);
    }

    private Comment changeAuthorReceiverCommentToRealNick(String articleAuthor, Comment comment) {
        if (comment.getReceiver().equals("Author")) {
            return new Comment(
                    comment.getAuthor(),
                    comment.getTitle(),
                    articleAuthor,
                    comment.getContent(),
                    comment.getDate()
            );
        } else return comment;
    }

    private String extractArticleAuthor(Document document) {
        Elements authorElement = document.select("div#blog-header div#blog-header-user div.author-box div.author-about-body a.author-nick");
        return authorElement.text();
    }

    // assumes articleCreated date is format like in examples:
    //        a) 9.37                (today so day,month,year implicit)
    //        b) 2.11.2014 18.04     (year explicit)
    //        c) 20.05 18.15         (year implicit - current)
    private Date extractArticleCreatedDate(Elements articleCreated) {
        String dateText = articleCreated.text();
        return extractDate(dateText);
    }

    // Extract date from string in specific salon24.pl date format
    private Date extractDate(String dateText) {
        String[] dateTime = dateText.split(" ");
        if (dateTime.length == 1) {
            Integer hours = new Integer(dateTime[0].split(":")[0]);
            Integer mins =  new Integer(dateTime[0].split(":")[1]);

            Integer day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            Integer month = Calendar.getInstance().get(Calendar.MONTH);
            Integer year = Calendar.getInstance().get(Calendar.YEAR);
            return new Date(year-1900,month,day,hours,mins);
        }
        String date = dateTime[0];
        String[] dateParts = date.split("\\.");

        String time = dateTime[1];
        String[] timeParts = time.split(":");

        Integer hours = new Integer(timeParts[0]);
        Integer mins =  new Integer(timeParts[1]);

        Integer day = Integer.parseInt(dateParts[0]);
        Integer month = Integer.parseInt(dateParts[1])-1; // Month in date constructor starts with 0
        Integer year = dateParts.length == 3 ? Integer.parseInt(dateParts[2]) : Calendar.getInstance().get(Calendar.YEAR);

        return new Date(year-1900,month,day,hours,mins);
    }

    private Comment extractComment(Element articleComment) {
        Elements articleCommentTitle = articleComment.select("h3");
        Elements articleCommentBody = articleComment.select("div.comment-body");
        Elements articleAuthorBox = articleComment.select("div.author-box");

        String commentTitle = articleCommentTitle.text();
        String commentBody = articleCommentBody.text();
        Date commentDate = extractCommentDate(articleAuthorBox);
        String commentAuthor = extractCommentAuthor(articleAuthorBox);
        String commentReceiver = extractReceiverOfComment(commentTitle);
        return new Comment(commentAuthor,commentTitle,commentReceiver,commentBody,commentDate);
    }

    private String extractReceiverOfComment(String commentTitle) {
        Pattern pattern = Pattern.compile("@\\w+");
        Matcher matcher = pattern.matcher(commentTitle);
        if (matcher.find() )
            return matcher.group().substring(1);
        return "";
    }

    private String extractCommentAuthor(Elements articleAuthorBox) {
        Elements authorAboutBody = articleAuthorBox.select(".author-about-body");
        Element authorOfComment = authorAboutBody.select("a.author-nick")
                .first();
        return authorOfComment.text();
    }

    private Date extractCommentDate(Elements articleAuthorBox) {
        Elements authorAboutBody = articleAuthorBox.select(".author-about-body");
        Element authorCommentDate = authorAboutBody.select("span.sep").first();
        Date commentDate = extractDate(authorCommentDate.text());
        return commentDate;
    }
}



















