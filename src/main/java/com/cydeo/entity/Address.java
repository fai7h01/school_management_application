package com.cydeo.entity;

import com.cydeo.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Address {

    @NotBlank(message = "Address is a required field")
    @Size(max = 50, min = 10, message = "Address must be between 10 and 50 characters long")
    private String addressInfo;


    @NotNull(message = "Please select a state.")
    private State state;


    @NotBlank(message = ("Phone number is a required field !"))
    @Pattern(regexp="^+1 (\\d{3}) \\d{3}-\\d{4}$", message = "USA phone numbers in the format +1 (XXX) XXX-XXXX")
    private String phoneNumber;





}
