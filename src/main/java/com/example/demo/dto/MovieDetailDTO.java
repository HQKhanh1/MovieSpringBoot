package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailDTO {
    private int id;
    private String title;
    private String poster;
    private String detail;
    private Boolean movieStatus;
    private String linkTrailer;
    private String linkMovie;
    private Date releaseDate;
    private Time movieDuration;
    private int viewNumber;
    //    @OneToMany(mappedBy = "movieDetail", cascade = CascadeType.ALL)
    private List<MovieEvaluateDTO> movieEvaluates;
    //    @ManyToMany
    private List<MovieCastDTO> movieCasts;
    //    @ManyToMany
    private List<MovieGenreDTO> movieGenres;
    //    @ManyToMany
    private List<MovieDirectorDTO> movieDirectors;

    public MovieDetailDTO(int id,
                          String title,
                          String poster,
                          String detail,
                          Boolean movieStatus,
                          String linkTrailer,
                          String linkMovie,
                          Date releaseDate,
                          Time movieDuration,
                          int viewNumber)
    {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.detail = detail;
        this.movieStatus = movieStatus;
        this.linkTrailer = linkTrailer;
        this.linkMovie = linkMovie;
        this.releaseDate = releaseDate;
        this.movieDuration = movieDuration;
        this.viewNumber = viewNumber;
    }
}
