package com.cydeo.service;

import com.cydeo.entity.Lesson;

import java.util.List;

public interface LessonService extends CrudService<Lesson, String> {

    List<Lesson> findAllLessonByCourseId(Long courseId);

}
