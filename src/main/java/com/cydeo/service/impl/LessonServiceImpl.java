package com.cydeo.service.impl;

import com.cydeo.entity.InstructorAssessment;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl extends AbstractMapService<Lesson, Long> implements LessonService {

    private final StudentService studentService;


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
    public Lesson findById(Long aLong) {
        return super.findById(aLong);
    }


    @Override
    public void update(Lesson lesson) {
        Lesson storedLesson = findById(lesson.getId());
        lesson.setStudents(storedLesson.getStudents());

        //TODO we should assign updated lesson to the relevant student.lessonGrade
        for (Student student : studentService.findAll()) {
            Map<Lesson, InstructorAssessment> lessonGrade = student.getLessonGrade();
            List<Lesson> lessonsToUpdate = new ArrayList<>();
            // Collect lessons to be updated
            for (Map.Entry<Lesson, InstructorAssessment> entry : lessonGrade.entrySet()) {
                if (entry.getKey().getId().equals(lesson.getId())) {
                    lessonsToUpdate.add(entry.getKey());
                }
            }
            // Update collected courses
            for (Lesson oldLesson : lessonsToUpdate) {
                InstructorAssessment value = lessonGrade.remove(oldLesson);
                lessonGrade.put(lesson, value);
            }
        }

        super.update(lesson.getId(), lesson);

    }


    @Override
    public List<Lesson> findAll() {
        return super.findAll();
    }


    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);

    }


    @Override
    public List<Lesson> findAllLessonByCourseId(Long courseId) {
        return findAll().stream()
                .filter(lesson -> lesson.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());
    }
}

