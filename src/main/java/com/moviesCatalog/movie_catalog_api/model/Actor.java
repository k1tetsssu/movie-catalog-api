package com.moviesCatalog.movie_catalog_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "actors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "birthYear")
    private Integer birthYear;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}