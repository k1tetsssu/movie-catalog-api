package com.moviesCatalog.movie_catalog_api.exception.DirectorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateDirectorException extends RuntimeException {
    public DuplicateDirectorException(String message) {
        super(message);
    }
}