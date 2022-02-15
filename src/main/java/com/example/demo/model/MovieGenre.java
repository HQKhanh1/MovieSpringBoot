package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_genre")
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Movie's genre cannot be empty")
    private String name;

    @ManyToMany(mappedBy = "movieGenres")
    @EqualsAndHashCode.Exclude
    private List<MovieDetail> movieDetails;
}
