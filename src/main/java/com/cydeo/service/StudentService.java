package com.cydeo.service;

import com.cydeo.entity.Student;

public interface StudentService extends CrudService <Student,String> {

    void enrollStudent(String username, Long courseId);
    void dropStudent(String username, Long courseId);

}
