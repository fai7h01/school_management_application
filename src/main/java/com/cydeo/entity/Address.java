package com.cydeo.entity;

import com.cydeo.enums.State;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Address {

    private String addressInfo;
    private State state;
    private String phoneNumber;



}
