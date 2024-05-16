package com.cydeo.entity;

import com.cydeo.enums.Gender;

public class Student {

    private String firstName ;
    private String lastName;
    private String email;
    private Gender gender;
    private Address address;
    private Map <Course,Boolean> courseStatus;
    private Map<Lesson, InstructorAssessment> lessonGrade;

}
