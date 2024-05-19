package com.cydeo.service.impl;

import com.cydeo.entity.Instructor;
import com.cydeo.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl extends AbstractMapService<Instructor, String> implements InstructorService {

    @Override
    public Instructor save(Instructor object) {
       return super.save(object.getUsername(), object);
    }

    @Override
    public Instructor findById(String username) {
        return super.findById(username);
    }

    @Override
    public List<Instructor> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String username) {
       super.deleteById(username);
    }

    @Override
    public void update(Instructor object) {
        super.update(object.getUsername(), object);

    }
}
