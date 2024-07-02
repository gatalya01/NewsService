package com.example.NewsService.ex;

public class UserUnmatchedException extends NewsApplicationException {
    public UserUnmatchedException() {
    }

    public UserUnmatchedException(String message) {
        super(message);
    }
}