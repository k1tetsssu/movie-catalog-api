package com.moviesCatalog.movie_catalog_api.exception.ActorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateActorException extends RuntimeException {
    public DuplicateActorException(String message) {
        super(message);
    }
}