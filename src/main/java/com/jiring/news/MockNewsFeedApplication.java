package com.jiring.news;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockNewsFeedApplication {
    public static void main(String[] args) {
        String newsAnalyzerHost = "127.0.0.1";
        int newsAnalyzerPort = 8686;
        int newsFrequency = Integer.parseInt(System.getProperty("newsFrequency", "5000"));
        MockNewsFeed mockNewsFeed = new MockNewsFeed(newsAnalyzerHost, newsAnalyzerPort, newsFrequency);
        new Thread(mockNewsFeed).start();
    }
}
