package dao;

import com.mongodb.*;
import crawler.api.ArticleContent;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ArticleContentDAO {
    public static String URL =
            "ds031932.mongolab.com";
    public static int PORT = 31932;
    public static String DB = "election_agents";
    public static String USER = "election_agents";
    public static String PASSWORD = "election_agents";

    private static ArticleContentDAO articleContentDAO;
    private final DB db;
    private final MongoCollection newsCollection;


    private ArticleContentDAO(DB db) {
        this.db = db;
        Jongo jongo = new Jongo(db);
        newsCollection = jongo.getCollection("News");
    }

    public static synchronized ArticleContentDAO getInstance() throws UnknownHostException {
        if (articleContentDAO == null) {
            MongoClient mongoClient = new MongoClient(URL,PORT);
            DB db = mongoClient.getDB(DB);
            db.authenticate(USER,PASSWORD.toCharArray());
            articleContentDAO = new ArticleContentDAO(db);
        }

        return articleContentDAO;
    }

    public void saveArticleContent(ArticleContent articleContent) {
        newsCollection.insert(articleContent);
    }


}
