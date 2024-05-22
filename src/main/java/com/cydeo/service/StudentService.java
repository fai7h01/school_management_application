package com.cydeo.service;

import com.cydeo.entity.Course;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;

public interface StudentService extends CrudService <Student,String> {


    void enrollStudent(String username, Long id);

    void assignCourseStudentsToNewLesson(Course course, Lesson lesson);

}
