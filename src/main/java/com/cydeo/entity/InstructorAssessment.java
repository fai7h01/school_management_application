package com.cydeo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InstructorAssessment {

    @NotBlank(message = "Message can not be empty!")
    private String instructorImpressionOfStudent ;
    @NotNull(message = "Grade can not be empty")
    private Long grade;
    private LocalDate date;




}
