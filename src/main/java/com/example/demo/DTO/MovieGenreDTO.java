package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieGenreDTO {
    private int id;
    private String name;
    //    @ManyToMany(mappedBy = "movieGenres")
    private List<MovieDetailDTO> movieDetails;

    public MovieGenreDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
