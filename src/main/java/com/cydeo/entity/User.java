package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@SQLRestriction("is_deleted is false")
public class User extends BaseEntity{

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String userName;

    private String password;

    private String confirmPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;




}


