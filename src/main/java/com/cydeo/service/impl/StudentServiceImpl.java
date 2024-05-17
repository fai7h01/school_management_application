package com.cydeo.service.impl;

import com.cydeo.entity.Course;
import com.cydeo.entity.Student;
import com.cydeo.service.CourseService;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl extends AbstractMapService<Student,String> implements StudentService {

    private final CourseService courseService;

    public StudentServiceImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Student save(Student student) {

        courseService.findAll().forEach(course -> student.getCourseStatus().put(course,false));

        return super.save(student.getEmail(), student);
    }

    @Override
    public Student findById(String email) {
        return super.findById(email);
    }

    @Override
    public List<Student> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Student student) {
        super.update(student.getEmail(), student);

    }

    @Override
    public void deleteById(String email) {
        super.deleteById(email);

    }


    @Override
    public void enrollStudent(String username, Long id) {
        Student student = findById(username);
        Course enrollCourse = courseService.findById(id);
        student.getCourseStatus().put(enrollCourse, true);
        //lesson service needed
    }
}
