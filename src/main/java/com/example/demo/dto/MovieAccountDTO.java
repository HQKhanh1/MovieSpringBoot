package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieAccountDTO {
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    private String email;
    private String avatar;
    private String firstname;
    private String lastname;
    private Date birthday;
    private int idTown;
    private String address;
    private String phoneNumber;
    private boolean gender;
    //Many To Many
    List<AccountRoleDTO> accountRoles;
    //    @OneToMany(mappedBy = "movieAccountInToken", cascade = CascadeType.ALL)
    private List<VerificationTokenDTO> verificationTokens;
    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserHistoryDTO> userHistories;
    //    @OneToMany(mappedBy = "movieAccountDetail", cascade = CascadeType.ALL)
    private List<MovieEvaluateDTO> movieEvaluates;

    public MovieAccountDTO(int id,
                           String username,
                           String password,
                           boolean enabled,
                           String email,
                           String avatar,
                           String firstname,
                           String lastname,
                           Date birthday,
                           int idTown,
                           String address,
                           String phoneNumber,
                           boolean gender)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.avatar = avatar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.idTown = idTown;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}
