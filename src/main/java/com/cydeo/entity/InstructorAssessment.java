package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assessments")
public class InstructorAssessment extends BaseEntity{

    private LocalDate gradeDate;
    private Long grade;
    private String instructorImpressionOfStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudentLesson studentLesson;

}
