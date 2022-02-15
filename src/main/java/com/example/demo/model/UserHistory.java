package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_history")
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "id_user")
    private MovieAccount user;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "id_movie")
    private MovieDetail movie;

    @Column(name = "history_date")
    @NotNull(message = "History date cannot be empty")
    @PastOrPresent(message = "History date is not great than present")
    private Date historyDate;
}
