package com.cydeo.service.impl;

import com.cydeo.entity.Course;
import com.cydeo.entity.Student;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service//like @Component - creates bean
public class CourseServiceImpl extends AbstractMapService<Course, Long> implements CourseService {



    private final StudentService studentService;
    private final LessonService lessonService;


    public CourseServiceImpl(@Lazy StudentService studentService, LessonService lessonService) {
        this.studentService = studentService;
        this.lessonService = lessonService;
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
        // During the update operation:
        // we should assign updated course to the relevant lessons.
        // we should assign updated course to the relevant student.courseStatus.
        //Todo if you update any course that means you should update all lessons' course field.
        lessonService.findAll().stream()
                .filter(lesson -> lesson.getCourse().getId().equals(course.getId()))
                .forEach(lesson -> lesson.setCourse(course));
        //Todo if you update any course that means you should update all students' courseStatus course information.
        for (Student student : studentService.findAll()) {
            Map<Course, Boolean> courseStatus = student.getCourseStatus();
            List<Course> coursesToUpdate = new ArrayList<>();
            // Collect courses to be updated
            for (Map.Entry<Course, Boolean> entry : courseStatus.entrySet()) {
                if (entry.getKey().getId().equals(course.getId())) {
                    coursesToUpdate.add(entry.getKey());
                }
            }
            // Update collected courses
            for (Course oldCourse : coursesToUpdate) {
                boolean value = courseStatus.remove(oldCourse);
                courseStatus.put(course, value);
            }
        }
        super.update(course.getId(), course);

    }

    @Override
    public void deleteById(Long id) {
        Course course = findById(id);
        //If the course is successfully deleted, show “This Course is successfully deleted“ message in the page.
        //Before deleting the course, remove this course from all students (courseStudent)
        studentService.findAll().forEach(s-> s.getCourseStatus().remove(course));
        super.deleteById(id);

    }

    @Override
    public boolean isNotEligibleToDelete(Long id) {
        Course foundCourse = findById(id);
        return lessonService.findAll().stream().anyMatch(l->l.getCourse().equals(foundCourse));

    }
}


