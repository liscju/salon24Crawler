package pl.edu.agh.agents.crawler.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class JsonDateSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        String formattedDate = dateFormat.format(date);

        gen.writeString(formattedDate);
    }

}

public class ArticleContent {
    private String link;
    private String title;
    private String author;
    @JsonSerialize(using=JsonDateSerializer.class)
    private Date created;
    private String mainContent;
    private List<Comment> comments;

    public ArticleContent() {
    }

    public ArticleContent(String link,String title, String author, Date created,String mainContent, List<Comment> comments) {
        this.link = link;
        this.title = title;
        this.author = author;
        this.created = created;
        this.mainContent = mainContent;
        this.comments = comments;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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

        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (mainContent != null ? !mainContent.equals(that.mainContent) : that.mainContent != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (mainContent != null ? mainContent.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
