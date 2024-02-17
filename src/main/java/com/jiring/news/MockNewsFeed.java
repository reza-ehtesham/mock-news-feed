package com.jiring.news;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class MockNewsFeed implements Runnable {
    private final String[] words = {"up", "down", "rise", "fall", "good", "bad", "success", "failure", "high", "low"};
    private final Random random = new Random();

    private final String newsAnalyzerHost;
    private final int newsAnalyzerPort;
    private final int newsFrequency;

    public MockNewsFeed(String newsAnalyzerHost, int newsAnalyzerPort, int newsFrequency) {
        this.newsAnalyzerHost = newsAnalyzerHost;
        this.newsAnalyzerPort = newsAnalyzerPort;
        this.newsFrequency = newsFrequency;
    }

    @Override
    public void run() {
        while (true) {
            try (Socket socket = new Socket(newsAnalyzerHost, newsAnalyzerPort);
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                String headline = generateRandomHeadline();
                int priority = generateRandomPriority();
                NewsItem newsItem = new NewsItem(headline, priority);
                System.out.println("try to send object");
                outputStream.writeObject(newsItem);
                Thread.sleep(newsFrequency);
            } catch (SocketException e) {
                System.err.println("Connection to NewsAnalyzer closed. Reconnecting...");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateRandomHeadline() {
        int numWords = random.nextInt(3) + 3;
        StringBuilder headline = new StringBuilder();

        for (int i = 0; i < numWords; i++) {
            int randomIndex = random.nextInt(words.length);
            headline.append(words[randomIndex]).append(" ");
        }

        return headline.toString().trim();
    }

    private int generateRandomPriority() {
        return random.nextInt(10);
    }
}