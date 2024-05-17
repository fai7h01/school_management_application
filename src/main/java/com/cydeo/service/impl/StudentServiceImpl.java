package com.cydeo.service.impl;

import com.cydeo.entity.Student;
import com.cydeo.service.CourseService;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentServiceImpl extends AbstractMapService<Student,String> implements StudentService {

    private final CourseService courseService;
    private static final Logger logger = Logger.getLogger(StudentServiceImpl.class.getName());

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
        return findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow();
    }

    @Override
    public List<Student> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Student student) {
        Student studentInDB = findById(student.getEmail());
        findAll().remove(studentInDB);
        save(student);
        logger.log(Level.INFO,"Print updated user after update");
        System.out.println(findById(student.getEmail()));

    }

    @Override
    public void deleteById(String email) {
        super.deleteById(email);

    }
}
