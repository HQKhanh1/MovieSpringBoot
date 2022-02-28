package com.example.demo.model;

import com.example.demo.model.Key.MovieEvaluateKey;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "move_evaluate")
public class MovieEvaluate {
    @EmbeddedId
    private MovieEvaluateKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "id_user")
    @EqualsAndHashCode.Exclude
    private Account accountDetail;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "id_movie")
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
