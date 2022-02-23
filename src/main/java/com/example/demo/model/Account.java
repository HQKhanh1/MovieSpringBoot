package com.example.demo.model;

import com.example.demo.model.address.Town;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_acc_id")
    private int id;

    @Column(name = "acc_name", unique = true)
    @NotBlank(message ="Username cannot be empty")
    private String username;

    @Column(name = "acc_password")
    @NotBlank(message ="Password cannot be empty")
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^[a-z0-9](.?[a-z0-9]){0,}@g(oogle)?mail.com$", message = "Invalid email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "firstname")
    @NotBlank(message = "Firstname cannot be empty")
    private String firstname;

    @Column(name = "lastname")
    @NotBlank(message = "Lastname cannot be empty")
    private String lastname;

    @Column(name = "birthday")
    @NotNull(message = "Birthday cannot be empty")
    private Date birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_commune_ward_town")
    @EqualsAndHashCode.Exclude
    private Town idTown;

    @Column(name = "address_detail")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    @NotNull(message = "Gender cannot be empty")
    private boolean gender;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<RoleForAccount> accountRoles;

    @OneToMany(mappedBy = "accountInToken", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<VerificationToken> verificationTokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<UserHistory> userHistories;

    @OneToMany(mappedBy = "accountDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<MovieEvaluate> movieEvaluates;

}
