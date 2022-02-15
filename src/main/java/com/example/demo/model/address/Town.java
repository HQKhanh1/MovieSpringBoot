package com.example.demo.model.address;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "commune_ward_town")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "id_district")
    @EqualsAndHashCode.Exclude
    private District district;

}
