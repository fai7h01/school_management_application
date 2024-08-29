package com.cydeo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorAssessmentDTO {

    private Long id;

    private StudentLessonDto studentLesson;

    private LocalDate gradeDate;
    @NotNull(message = "Grade can not be empty!")
    private Long grade;
    @NotBlank(message = "Message can not be empty!")
    private String instructorImpressionOfStudent;
}