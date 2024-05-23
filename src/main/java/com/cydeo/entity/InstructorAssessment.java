package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InstructorAssessment {

    private String instructorImpressionOfStudent ;
    private Long grade;
    private LocalDate date;




}
