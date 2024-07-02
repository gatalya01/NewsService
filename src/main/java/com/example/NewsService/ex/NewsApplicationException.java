package com.example.NewsService.ex;

public class NewsApplicationException extends Exception {
    public NewsApplicationException() {
    }

    public NewsApplicationException(String message) {
        super(message);
    }
}