package com.lordpvi.articles.infrastructure.error;

public class AppException extends RuntimeException {
    public AppException(Exception e) {
        super(e);
    }
}
