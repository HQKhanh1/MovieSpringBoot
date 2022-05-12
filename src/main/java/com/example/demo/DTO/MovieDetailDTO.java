package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

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
    private LocalDate releaseDate;
    private Time movieDuration;
    private int viewNumber;
}
