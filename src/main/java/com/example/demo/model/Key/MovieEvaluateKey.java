package com.example.demo.model.Key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEvaluateKey implements Serializable {
    @Column(name = "idUser")
    Integer userId;

    @Column(name = "idMovie")
    Integer movieId;
}
