package com.cydeo.converter;

import com.cydeo.entity.Course;
import com.cydeo.service.CourseService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationPropertiesBinding?
public class CourseConverter implements Converter <String, Course>{

    CourseService courseService;

    public CourseConverter (CourseService courseService) {
        this.courseService = courseService;
    }


    @Override
    public Course convert(String source) {
       if(source == null || source.equals("")){
           return null;
       }
       return courseService.findById(Long.parseLong(source));
    }
}
