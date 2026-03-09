package com.moviesCatalog.movie_catalog_api.model;

import jakarta.persistence.*; // библиотека для работы с базой данных через Java
import lombok.*;

import java.util.List;

@Entity // Этот класс является сущностью базы данных
@Table(name = "movies")
@Data
@NoArgsConstructor //Создает пустой конструктор
@AllArgsConstructor // Создает конструктор со всеми параметрами:
@Builder // Позволяет удобно создавать объект
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерируется ID автоматически
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "releaseYear")
    private Integer releaseYear;

    @Column(nullable = false, name = "rating")
    private Double rating;

    @ManyToOne // много фильмов - один жанр
    @JoinColumn(name = "genre_id") // в таблице movies будет колонка genre_id
    private Genre genre;

    @ManyToOne // много фильмов - один режиссер
    @JoinColumn(name = "director_id") // // в таблице movies будет колонка director
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;
}