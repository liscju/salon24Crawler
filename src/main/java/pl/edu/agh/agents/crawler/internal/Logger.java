package pl.edu.agh.agents.crawler.internal;


public class Logger {

    public static void logException(String errorMessage,Exception exception) {
        System.out.println(errorMessage);
        System.out.println(exception.toString());
    }
}
