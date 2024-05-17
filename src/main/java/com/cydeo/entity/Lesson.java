package com.cydeo.entity;

import com.cydeo.service.CrudService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    private String id;
    private String name;
    private String description;
    private Role instructor;
    private Course course;
    private Student student;
}
