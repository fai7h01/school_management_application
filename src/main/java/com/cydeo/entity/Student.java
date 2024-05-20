package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    @NotBlank(message = "First Name is a required field")
    @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long")
    @Pattern(regexp = "[A-Z]\\w*", message = "First Name must starts with Uppercase character")
    private String firstName ;
    @NotBlank(message = "First Name is a required field")
    @Size(max = 15, min = 2, message = "First Name must be between 2 and 15 characters long")
    @Pattern(regexp = "[A-Z]\\w*", message = "First Name must starts with Uppercase character")
    private String lastName;
    @Email(message = "Email is a required field")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Provide a suitable email address ex: michael23jordan23@test.com")
    private String email;
    @NotNull(message = "Please select a Gender")
    private Gender gender;
    @Valid
    @NotNull
    private Address address;
    private Map<Course,Boolean> courseStatus = new HashMap<>();
    private Map<Lesson, InstructorAssessment> lessonGrade;


    public Student(String firstName, String lastName, String email, Gender gender, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
    }
}
