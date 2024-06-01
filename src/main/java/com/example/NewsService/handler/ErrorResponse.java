package com.example.NewsService.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private List<String> details;

    public ErrorResponse(String message, String detail) {
        this.message = message;
        this.details = new ArrayList<>();
        this.details.add(detail);
    }
}