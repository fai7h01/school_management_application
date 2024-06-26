package com.cydeo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class Lesson {
    private Long id;

    @NotBlank(message = "Lesson Name is a required field")
    @Size(max = 40, min = 2, message = "Lesson Name must be between 2 and 40 characters long")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$", message = "Lesson Name must use alphabetic characters with spaces")
    private String name;

    @NotBlank(message = "lesson Description is a required field")
    @Size(max = 100, min = 5, message = "lesson Description must be between 5 and 100 characters long")
    private String description;

    @NotNull(message = "please select a lesson instructor")
    private User instructor;

    @NotNull(message = "please select a course")
    private Course course;

    private List<Student> students = new ArrayList<>();

    public Lesson(Long id, String name, String description, User instructor, Course course) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructor = instructor;
        this.course = course;
    }
}
