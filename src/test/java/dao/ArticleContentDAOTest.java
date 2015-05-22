package dao;

import crawler.api.ArticleContent;
import crawler.api.Comment;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by lee on 2015-05-22.
 */
public class ArticleContentDAOTest {

    @Test
    public void testGetInstance() throws Exception {
        ArticleContentDAO articleContentDAO =
                ArticleContentDAO.getInstance();
        articleContentDAO.saveArticleContent(
                new ArticleContent("Hejka",
                        Calendar.getInstance().getTime(),
                        "Oto moj pierwszy koment",
                        Arrays.asList(
                                new Comment("Pierwszy","LOOL"),
                                new Comment("Drugi","Noob")
                        )
                )
        );
    }
}