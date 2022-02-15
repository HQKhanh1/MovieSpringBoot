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
@Table(name = "movie_driector")
public class MovieDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "avatar")
    @NotBlank(message = "Director's avatar cannot be empty")
    private String avatar;

    @Column(name = "name")
    @NotBlank(message = "Director's name cannot be empty")
    private String name;

    @Column(name = "story")
    private String story;

    @ManyToMany(mappedBy = "movieDirectors")
    @EqualsAndHashCode.Exclude
    private List<MovieDetail> movieDetails;
}
