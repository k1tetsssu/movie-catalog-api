package com.moviesCatalog.movie_catalog_api.service;

import com.moviesCatalog.movie_catalog_api.exception.MovieException.DuplicateMovieException;
import com.moviesCatalog.movie_catalog_api.exception.GenreException.GenreNotFoundException;
import com.moviesCatalog.movie_catalog_api.exception.MovieException.InvalidMovieDataException;
import com.moviesCatalog.movie_catalog_api.model.Genre;
import com.moviesCatalog.movie_catalog_api.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service // аннотация, показывающая что это сервисный компонент
public class GenreService {

    private final GenreRepository genreRepository; // Это ссылка на Repository, который работает с базой данных.


    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    } // SELECT * FROM movies

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    } // SELECT * FROM movies WHERE id = ?

    public Genre saveGenre(Genre genre) {

        if (genre.getName() == null || genre.getName().isEmpty()) {
            throw new InvalidMovieDataException("Название Жанра не может быть пустым");
        }
        if (genreRepository.existsByName(genre.getName())) {
            throw new DuplicateMovieException("Жанр с таким названием уже существует");
        }
        return genreRepository.save(genre);
    } // INSERT INTO movies ...

    public Genre updateGenre(Long id, Genre updatedGenre) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));

        genre.setName(updatedGenre.getName());

        if (genre.getName() == null || genre.getName().isEmpty()) {
            throw new InvalidMovieDataException("Название Жанра не может быть пустым");
        }
        if (genreRepository.existsByName(genre.getName())) {
            throw new DuplicateMovieException("Жанр с таким названием уже существует");
        }
        return genreRepository.save(genre);
    }

    public Genre patchGenre(Long id, Genre updatedGenre) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));

        if (updatedGenre.getName() != null) {
            genre.setName(updatedGenre.getName());
        }
        if (genre.getName() == null || genre.getName().isEmpty()) {
            throw new InvalidMovieDataException("Название Жанра не может быть пустым");
        }
        if (genreRepository.existsByName(genre.getName())) {
            throw new DuplicateMovieException("Жанр с таким названием уже существует");
        }
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        if(!genreRepository.existsById(id)) {
            throw new GenreNotFoundException(id);
        }
        genreRepository.deleteById(id);
    } // DELETE FROM movies WHERE id = ?
}