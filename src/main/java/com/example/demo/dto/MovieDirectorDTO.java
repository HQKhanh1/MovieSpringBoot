package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDirectorDTO {
    private int id;
    private String avatar;
    private String name;
    private String story;
    //    @ManyToMany(mappedBy = "movieDirectors")
    private List<MovieDetailDTO> movieDetails;

    public MovieDirectorDTO(int id, String avatar, String name, String story) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.story = story;
    }
}
