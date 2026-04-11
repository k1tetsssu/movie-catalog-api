package com.moviesCatalog.movie_catalog_api.exception.GenreException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidGenreDataException extends RuntimeException{
    public InvalidGenreDataException(String message){
        super(message);
    }
}
