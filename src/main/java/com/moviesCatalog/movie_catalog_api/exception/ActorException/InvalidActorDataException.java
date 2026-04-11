package com.moviesCatalog.movie_catalog_api.exception.ActorException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidActorDataException extends RuntimeException {
    public InvalidActorDataException(String message) {
        super(message);
    }
}