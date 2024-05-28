package com.cydeo.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Long id;

    @NotBlank(message = "Course Name is a required field")
    @Size(max = 40, min = 2, message = "Course Name must be between 2 and 40 characters long")
    //@Pattern(regexp = "([A-Za-z]\\w(?:\\s[A-Za-z]\\w*)*)", message = "Course Name must use alphabetic characters with spaces")
    @Pattern(regexp = "([A-Za-z]+(?:\s[A-Za-z]+)*)", message = "Course Name must use alphabetic characters with spaces")
    private String name;

    @NotBlank(message = "Course Description is a required field")
    @Size(max = 100, min = 5, message = "Course Description must be between 5 and 100 characters long")
    private String description;

    @NotNull(message = "Select a Course Manager")
    private User courseManager;

    @NotNull(message = "Select a start date")
    //@DateTimeFormat(pattern="mm/dd/yyyy")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "Select an end date")
    //@DateTimeFormat(pattern="mm/dd/yyyy")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;


}
