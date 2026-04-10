package com.moviesCatalog.movie_catalog_api.exception;

// Genre import
import com.moviesCatalog.movie_catalog_api.exception.GenreException.DuplicateGenreException;
import com.moviesCatalog.movie_catalog_api.exception.GenreException.GenreNotFoundException;
import com.moviesCatalog.movie_catalog_api.exception.GenreException.InvalidGenreDataException;

// Movie import
import com.moviesCatalog.movie_catalog_api.exception.MovieException.DuplicateMovieException;
import com.moviesCatalog.movie_catalog_api.exception.MovieException.InvalidMovieDataException;
import com.moviesCatalog.movie_catalog_api.exception.MovieException.MovieNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Actor
import com.moviesCatalog.movie_catalog_api.exception.ActorException.ActorNotFoundException;
import com.moviesCatalog.movie_catalog_api.exception.ActorException.InvalidActorDataException;
import com.moviesCatalog.movie_catalog_api.exception.ActorException.DuplicateActorException;
// Director
import com.moviesCatalog.movie_catalog_api.exception.DirectorException.DirectorNotFoundException;
import com.moviesCatalog.movie_catalog_api.exception.DirectorException.InvalidDirectorDataException;
import com.moviesCatalog.movie_catalog_api.exception.DirectorException.DuplicateDirectorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Genre
    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGenreNotFound(GenreNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                404,
                java.time.LocalDateTime.now().toString()
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(InvalidGenreDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidData(InvalidGenreDataException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                400,
                java.time.LocalDateTime.now().toString()
        );

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(DuplicateGenreException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateGenreException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                409,
                java.time.LocalDateTime.now().toString()
        );

        return ResponseEntity.status(409).body(error);
    }

    // Movie
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMovieNotFound(MovieNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                404,
                java.time.LocalDateTime.now().toString()
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(InvalidMovieDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidData(InvalidMovieDataException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                400,
                java.time.LocalDateTime.now().toString()
        );

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(DuplicateMovieException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateMovieException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                409,
                java.time.LocalDateTime.now().toString()
        );

        return ResponseEntity.status(409).body(error);
    }

    // Actor
    @ExceptionHandler(ActorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleActorNotFound(ActorNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                404,
                java.time.LocalDateTime.now().toString()
        );
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(InvalidActorDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidActorData(InvalidActorDataException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                400,
                java.time.LocalDateTime.now().toString()
        );
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(DuplicateActorException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateActor(DuplicateActorException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                409,
                java.time.LocalDateTime.now().toString()
        );
        return ResponseEntity.status(409).body(error);
    }

    // Director
    @ExceptionHandler(DirectorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDirectorNotFound(DirectorNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                404,
                java.time.LocalDateTime.now().toString()
        );
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(InvalidDirectorDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDirectorData(InvalidDirectorDataException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                400,
                java.time.LocalDateTime.now().toString()
        );
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(DuplicateDirectorException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateDirector(DuplicateDirectorException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                409,
                java.time.LocalDateTime.now().toString()
        );
        return ResponseEntity.status(409).body(error);
    }

    // Global
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                500,
                java.time.LocalDateTime.now().toString()
        );

        return ResponseEntity.status(500).body(error);
    }

}


