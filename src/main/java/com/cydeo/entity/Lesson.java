package com.cydeo.entity;

import com.cydeo.service.CrudService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson
    {
    private String id;
    private Student student;

    @NotBlank(message = "Lesson Name is a required field")
    @Size(max = 40, min = 2, message = "Lesson Name must be between 2 and 40 characters long")
    @Pattern(regexp = "([A-Za-z]\\w(?:\\s[A-Za-z]\\w*)*)", message = "Lesson Name must use alphabetic characters with spaces")
    private String name;

    @NotBlank(message = "lesson Description is a required field")
    @Size(max = 100, min = 5, message = "lesson Description must be between 5 and 100 characters long")
    private String description;

    @NotNull(message = "please select a lesson instructor")
    private Role instructor;

    @NotNull(message = "please select a course")
    private Course course;
    }
