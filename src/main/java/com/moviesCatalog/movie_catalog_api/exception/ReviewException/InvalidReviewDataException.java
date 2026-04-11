package com.moviesCatalog.movie_catalog_api.exception.ReviewException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidReviewDataException extends RuntimeException {
    public InvalidReviewDataException(String message) {
        super(message);
    }
}