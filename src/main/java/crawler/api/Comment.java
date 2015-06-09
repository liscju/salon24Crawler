package crawler.api;

import java.util.Date;

public class Comment {
    private String author;
    private String title;
    private String content;
    private Date date;

    public Comment() {
    }

    public Comment(String author,String title, String content, Date date) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (author != null ? !author.equals(comment.author) : comment.author != null) return false;
        if (title != null ? !title.equals(comment.title) : comment.title != null) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (date != null ? !date.equals(comment.date) : comment.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
