package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCastDTO {
    private int id;
    private String avatar;
    private String name;
    private String story;
//    @ManyToMany(mappedBy = "movieCasts")
    private List<MovieDetailDTO> movieDetails;

    public MovieCastDTO(int id, String avatar, String name, String story) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.story = story;
    }
}
