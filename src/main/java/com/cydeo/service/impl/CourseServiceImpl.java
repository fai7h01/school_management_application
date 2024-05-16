package com.cydeo.service.impl;

import com.cydeo.entity.Course;
import com.cydeo.service.CourseService;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service//like @Component - creates bean
public class CourseServiceImpl extends AbstractMapService<Course, Long> implements CourseService {


    private final StudentService studentService;

    public CourseServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Course save(Course course) {
        //Requirement: Assign new id to the course by UUID library
        if (course.getId() == null)
            course.setId(UUID.randomUUID().getMostSignificantBits());
        //Requirement: before saving a new course, set all students with status "false"
        studentService.findAll().forEach(student -> student.getCourseStatus().put(course, false));

        return super.save(course.getId(), course);
    }

    @Override
    public Course findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return super.findAll();
    }

    @Override
    public void update(Course course) {
        //before update new course, should we do anything in term of status???
        super.update(course.getId(), course);

    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}


