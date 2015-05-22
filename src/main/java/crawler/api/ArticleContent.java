package crawler.api;

import java.util.Date;
import java.util.List;

public class ArticleContent {
    private String title;
    private Date created;
    private String mainContent;
    private List<Comment> comments;

    public ArticleContent() {
    }

    public ArticleContent(String title, Date created,String mainContent, List<Comment> comments) {
        this.title = title;
        this.created = created;
        this.mainContent = mainContent;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreated() {
        return created;
    }

    public String getMainContent() {
        return mainContent;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleContent that = (ArticleContent) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (mainContent != null ? !mainContent.equals(that.mainContent) : that.mainContent != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (mainContent != null ? mainContent.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
