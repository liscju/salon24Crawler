package crawler.api;

import java.util.List;

public class ArticleContent {
    private final String title;
    private String mainContent;
    private List<String> comments;

    public ArticleContent(String title, String mainContent, List<String> comments) {
        this.title = title;
        this.mainContent = mainContent;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public String getMainContent() {
        return mainContent;
    }

    public List<String> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleContent that = (ArticleContent) o;

        if (mainContent != null ? !mainContent.equals(that.mainContent) : that.mainContent != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mainContent != null ? mainContent.hashCode() : 0;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
