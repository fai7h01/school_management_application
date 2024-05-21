package com.cydeo.service;

import com.cydeo.entity.Lesson;

import java.util.List;

public interface LessonService extends CrudService<Lesson, Long> {

    List<Lesson> findAllLessonByCourseId(Long courseId);

}
