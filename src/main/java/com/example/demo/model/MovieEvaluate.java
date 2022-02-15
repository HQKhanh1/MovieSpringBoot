package com.example.demo.model;

import com.example.demo.model.Key.MovieEvaluateKey;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
@Table(name = "move_evaluate")
public class MovieEvaluate {
    @EmbeddedId
    MovieEvaluateKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "idUser")
    @EqualsAndHashCode.Exclude
    private MovieAccount movieAccountDetail;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "idMovie")
    @EqualsAndHashCode.Exclude
    private MovieDetail movieDetail;

    @Column(name = "evaluate_time")
    @PastOrPresent(message = "Evaluate time is not greater than present")
    private Date evaluateTime;

    @Column(name = "evaluate_content")
    private String evaluateContent;

    @Column(name = "evaluate_rate")
    @NotNull(message = "Evaluate rate cannot be null")
    private int evaluateRate;

}
