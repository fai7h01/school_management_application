package com.cydeo.service.impl;

import com.cydeo.entity.*;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        super.update(student.getEmail(), student);

    }

    @Override
    public void deleteById(String email) {
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
        List<Student>  studentList=
        lessonService.findAll().stream().filter(lesson-> lesson.getCourse().getId().equals(course.getId()))
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
                .filter(lesson -> lesson.getInstructor().getUserName().equals(username)) // dersleri belirli eğitmene göre filtrele
                .flatMap(lesson -> lesson.getStudents().stream() // filtrelenen her dersi o dersle ilişkili öğrenciye göre streame al
                        .flatMap(student -> student.getLessonGrade().keySet().stream() //  her öğrenci için o öğrenciyle ilişkili ders notlarının akışını düzleştirir, böylece bireysel ders notlarıyla çalışabiliriz
                                .filter(lesson1 -> lesson1.getInstructor().getUserName().equals(username)) // tekrar filtrele ?
                                .map(lesson1 -> new StudentLesson(student, lesson1)) // filtrelenen her ders notunu bir studentLesson nesnesiyle eşle
                        )
                )
                .distinct() // çiftleri akıştan kaldır!
                .collect(Collectors.toList()); // listele
    }



}
