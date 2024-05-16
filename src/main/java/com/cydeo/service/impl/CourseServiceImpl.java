package com.cydeo.service.impl;

import com.cydeo.entity.Course;
import com.cydeo.service.CourseService;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service//like @Component - creates bean
public class CourseServiceImpl extends AbstractMapService <Course, Long> implements CourseService {



    private final StudentService studentService;

    public CourseServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Course save(Course course) {
        //Requirement: Assign new id to the course by UUID library
        if (course.getId() == null)
            course.setId(UUID.randomUUID().getMostSignificantBits());

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
        //- Courses with lessons cannot be deleted. (In that case, the page provide this message “This Course has either one or more than one lessons. Not allowed to delete“.)
        //- If the course is successfully deleted, show “This Course is successfully deleted“ message in the page.
        //- Before deleting the course, remove this course from all students (courseStudent)

        // Need lesson info and student info to complete this part

        // Draft of solution
        // Find the course by id
        Course course = findById(id);
        /*
        // Check if the course exists
        if (course != null) {
            // Check if the course has any lessons
            if (!course.getLessons().isEmpty()) {
                // Throw an exception or provide a message indicating that the course has lessons
                throw new IllegalStateException("This Course has one or more lessons. Not allowed to delete.");
            } else {
                // Remove the course from all students
                course.getCourseStudents().forEach(student -> student.getCourses().remove(course));
                // Delete the course
                super.deleteById(id);
                // Show a success message
                System.out.println("This Course is successfully deleted.");
            }
        } else {
            // Show a message indicating that the course does not exist
            System.out.println("Course not found with id: " + id);
        }

    }
    */
    }
}

