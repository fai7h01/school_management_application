package com.cydeo.service;

import com.cydeo.entity.Course;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.entity.StudentLesson;

import java.util.List;

public interface StudentService extends CrudService <Student,String> {

    void enrollStudent(String username, Long courseId);
    void dropStudent(String username, Long courseId);

    void assignCourseStudentsToNewLesson(Course course, Lesson lesson);
    public List<StudentLesson> findStudentsByInstructor(String username);
    void removeDeletedLessonFromStudents(Long lessonId);

}
