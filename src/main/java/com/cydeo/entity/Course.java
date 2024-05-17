package com.cydeo.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Long id;

    @NotBlank(message = "Course Name is a required field")
    @Size(max = 40, min = 2, message = "Course Name must be between 2 and 40 characters long")
    @Pattern(regexp = "([A-Za-z]\\w(?:\\s[A-Za-z]\\w*)*)", message = "Course Name must use alphabetic characters with spaces")
    private String name;

    @NotBlank(message = "Course Description is a required field")
    @Size(max = 100, min = 5, message = "Course Description must be between 5 and 100 characters long")
    private String description;

    @NotNull(message = "Select a Course Manager")
    private User courseManager;//need converter

    @NotNull(message = "Select a start date")
    @DateTimeFormat(pattern="mm/dd/yyyy")
    private LocalDate startDate;

    @NotNull(message = "Select an end date")
    @DateTimeFormat(pattern="mm/dd/yyyy")
    private LocalDate endDate;


}
