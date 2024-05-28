package com.cydeo.service;

import com.cydeo.entity.Course;

public interface CourseService extends CrudService <Course, Long> {

    public boolean isNotEligibleToDelete(Long id);

}
