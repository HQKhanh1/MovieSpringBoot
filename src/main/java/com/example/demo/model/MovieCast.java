package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_genre")
public class MovieCast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "avatar")
    @NotNull(message = "Cast's avatar cannot be empty")
    private String avatar;

    @Column(name = "name")
    @NotBlank(message = "Cast's name cannot be empty")
    private String name;

    @Column(name = "story")
    private String story;

    @OneToMany(mappedBy = "movieCast", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<FKCast> fkCasts;
}
