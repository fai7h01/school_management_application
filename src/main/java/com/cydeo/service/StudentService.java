package com.cydeo.service;

import com.cydeo.entity.*;

import java.util.List;

public interface StudentService extends CrudService <Student,String> {

    void enrollStudent(String username, Long courseId);
    void dropStudent(String username, Long courseId);

    void assignCourseStudentsToNewLesson(Course course, Lesson lesson);
    public List<StudentLesson> findStudentsByInstructor(String username);
    void removeDeletedLessonFromStudents(Long lessonId);

    public void assessStudent(InstructorAssessment instructorAssessment, String email, Lesson lesson);

}
