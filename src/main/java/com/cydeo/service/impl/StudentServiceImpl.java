package com.cydeo.service.impl;

import com.cydeo.entity.Student;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl extends AbstractMapService<Student,String> implements StudentService {
    @Override
    public Student save(Student student) {
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
}
