package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private Role role;
    private Gender gender;
    private Address address;



}


