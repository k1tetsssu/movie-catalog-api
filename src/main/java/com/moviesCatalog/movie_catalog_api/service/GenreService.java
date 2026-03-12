package com.moviesCatalog.movie_catalog_api.service;

import com.moviesCatalog.movie_catalog_api.model.Genre;
import com.moviesCatalog.movie_catalog_api.model.Movie;
import com.moviesCatalog.movie_catalog_api.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // аннотация, показывающая что это сервисный компонент
public class GenreService {

    private final GenreRepository genreRepository; // Это ссылка на Repository, который работает с базой данных.
    // Конструктор
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    } // Spring автоматически передаст Repository - Dependency Injection

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    } // SELECT * FROM movies

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    } // SELECT * FROM movies WHERE id = ?

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    } // INSERT INTO movies ...

    public Genre updateGenre(Long id, Genre updatedGenre) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        genre.setName(updatedGenre.getName());

        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {

        genreRepository.deleteById(id);
    } // DELETE FROM movies WHERE id = ?
}