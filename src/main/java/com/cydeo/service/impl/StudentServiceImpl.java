package com.cydeo.service.impl;

import com.cydeo.entity.*;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends AbstractMapService<Student, String> implements StudentService {

    private final CourseService courseService;
    private final LessonService lessonService;


    public StudentServiceImpl(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @Override
    public Student save(Student student) {

        courseService.findAll().forEach(course -> student.getCourseStatus().put(course, false));


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
        Student storedStudent = findById(student.getEmail());
        student.setCourseStatus(storedStudent.getCourseStatus());
        student.setLessonGrade(storedStudent.getLessonGrade());


        for (Lesson lesson : lessonService.findAll()) {
            List<Student> updatedStudentsList = new ArrayList<>();

            for (Student studentInLesson : lesson.getStudents()) {
                if (studentInLesson.getEmail().equals(student.getEmail())) {
                    updatedStudentsList.add(student);
                } else {
                    updatedStudentsList.add(studentInLesson);
                }
            }
            lesson.setStudents(updatedStudentsList);
        }

        super.update(student.getEmail(), student);

    }

    @Override
    public void deleteById(String email) {
        for (Lesson lesson : lessonService.findAll()) {
            lesson.getStudents().removeIf(student -> student.getEmail().equals(email));
        }
        super.deleteById(email);
    }

    @Override
    public void enrollStudent(String username, Long courseId) {
        Student student = findById(username);
        Course enrollCourse = courseService.findById(courseId);
        student.getCourseStatus().put(enrollCourse, true);
        lessonService.findAllLessonByCourseId(courseId)
                .forEach(lesson -> {
                    student.getLessonGrade().put(lesson, new InstructorAssessment("No Assessment", 0L, LocalDate.now()));
                    lesson.getStudents().add(student);
                });
    }

    @Override
    public void assignCourseStudentsToNewLesson(Course course, Lesson createdLesson) {
        List<Student> studentList =
                lessonService.findAll().stream().filter(lesson -> lesson.getCourse().getId().equals(course.getId()))
                        .flatMap(lesson -> lesson.getStudents().stream()).collect(Collectors.toList());
        createdLesson.setStudents(studentList);
        studentList.forEach(student -> student.getLessonGrade().put(createdLesson,
                new InstructorAssessment("No Assessment", 0L, LocalDate.now())));

    }

    public void dropStudent(String username, Long courseId) {
        Student student = findById(username);
        Course enrolledCourse = courseService.findById(courseId);
        student.getCourseStatus().put(enrolledCourse, false);
        lessonService.findAllLessonByCourseId(courseId)
                .forEach(lesson -> {
                    student.getLessonGrade().remove(lesson);
                    lesson.getStudents().remove(student);
                });
    }


    @Override
    public List<StudentLesson> findStudentsByInstructor(String username) {

        return lessonService.findAll().stream()
                .filter(lesson -> lesson.getInstructor().getUserName().equals(username))
                .flatMap(lesson -> lesson.getStudents().stream()
                        .flatMap(student -> student.getLessonGrade().keySet().stream()
                                .filter(lesson1 -> lesson1.getInstructor().getUserName().equals(username))
                                .map(lesson1 -> new StudentLesson(student, lesson1))
                        )
                )
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void removeDeletedLessonFromStudents(Long lessonId) {
        Lesson lesson = lessonService.findById(lessonId);
        for (Student student : lesson.getStudents()) {
            student.getLessonGrade().remove(lesson);
        }
    }

    @Override
    public void assessStudent(InstructorAssessment instructorAssessment, String email, Lesson lesson) {
        instructorAssessment.setDate(LocalDate.now());
        findById(email).getLessonGrade().put(lesson, instructorAssessment);
    }


}
