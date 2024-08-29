package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLRestriction("is_deleted is false")
public class StudentLesson extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    public Lesson lesson;
    @ManyToOne(fetch = FetchType.LAZY)
    public Student student;
}
