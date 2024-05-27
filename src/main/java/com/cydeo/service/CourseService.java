package com.cydeo.service;

import com.cydeo.entity.Course;

import java.util.UUID;

public interface CourseService extends CrudService <Course, Long> {

    public boolean isNotEligibleToDelete(Long id);

}
