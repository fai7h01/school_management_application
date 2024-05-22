package com.cydeo.service.impl;

import com.cydeo.entity.Lesson;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl extends AbstractMapService<Lesson, Long> implements LessonService {
    private  final StudentService studentService;


    public LessonServiceImpl(@Lazy StudentService studentService) {
        this.studentService = studentService;
    }


    @Override
    public Lesson save(Lesson lesson) {
        if (lesson.getId() == null)
            lesson.setId(UUID.randomUUID().getMostSignificantBits());

        studentService.assignCourseStudentsToNewLesson(lesson.getCourse(), lesson);
        return super.save(lesson.getId(), lesson);
    }

    @Override
    public void update(Lesson lesson) {

    }

    @Override
    public Lesson findById(Long id) {
        return null;
    }

    @Override
    public List<Lesson> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Lesson> findAllLessonByCourseId(Long courseId) {
        return findAll().stream()
                .filter(lesson -> lesson.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());
    }
}

