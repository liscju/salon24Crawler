package dao;

import com.mongodb.*;
import crawler.api.ArticleContent;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

public class NewsContentDAO {
    public static String URL =
            "ds031932.mongolab.com";
    public static int PORT = 31932;
    public static String DB = "election_agents";
    public static String USER = "election_agents";
    public static String PASSWORD = "election_agents";

    private static NewsContentDAO newsContentDAO;
    private final DB db;
    private final MongoCollection newsCollection;


    private NewsContentDAO(DB db) {
        this.db = db;
        Jongo jongo = new Jongo(db);
        newsCollection = jongo.getCollection("News");
    }

    public static synchronized NewsContentDAO getInstance() throws UnknownHostException {
        if (newsContentDAO == null) {
            MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();
            optionsBuilder.connectionsPerHost(1);
            optionsBuilder.maxWaitTime(10000);
            optionsBuilder.socketKeepAlive(true);
            optionsBuilder.threadsAllowedToBlockForConnectionMultiplier(1);
            MongoClientOptions options = optionsBuilder.build();
            MongoClient mongoClient = new MongoClient(URL+":"+PORT,options);
            DB db = mongoClient.getDB(DB);
            db.authenticate(USER,PASSWORD.toCharArray());
            newsContentDAO = new NewsContentDAO(db);
        }

        return newsContentDAO;
    }

    public void saveArticleContent(ArticleContent articleContent) {
        newsCollection.insert(articleContent);
    }


}
