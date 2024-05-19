package com.cydeo.bootstrap;

import com.cydeo.entity.*;
import com.cydeo.enums.Gender;
import com.cydeo.enums.State;
import com.cydeo.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final CourseService courseService;
    private final LessonService lessonService;
    private final StudentService studentService;
    private final InstructorService instructorService;

    public DataGenerator(UserService userService, RoleService roleService, CourseService courseService, LessonService lessonService, StudentService studentService, InstructorService instructorService) {
        this.userService = userService;
        this.roleService = roleService;
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.studentService = studentService;
        this.instructorService = instructorService;
    }


    @Override
    public void run(String... args) throws Exception {

        Role admin = new Role(1L, "Admin");
        Role manager = new Role(2L, "Manager");
        Role instructor = new Role(3L, "Instructor");

        roleService.save(admin);
        roleService.save(manager);
        roleService.save(instructor);


        Address address1 = new Address("123 Main Street Anytown 12345", "+1 (555) 555-1234", State.CALIFORNIA);
        Address address2 = new Address("456 Oak Avenue Smallville 67890", "+1 (555) 555-5678", State.TEXAS);
        Address address3 = new Address("789 Maple Drive Suburbia 45678", "+1 (555) 555-9012", State.NEW_YORK);
        Address address4 = new Address("321 Elm Street Springfield 23456", "+1 (555) 555-2456", State.ILLINOIS);
        Address address5 = new Address("567 Pine Road Lakeside 78901", "+1 (555) 555-7890", State.FLORIDA);
        Address address6 = new Address("789 Maple Drive Suburbia 45678", "+1 (555) 555-9012", State.NEW_YORK);
        Address address7 = new Address("321 Elm Street Springfield 23456", "+1 (555) 555-3456", State.ILLINOIS);
        Address address8 = new Address("890 Cedar Lane Mountainview 56789", "+1 (555) 555-2345", State.COLORADO);
        Address address9 = new Address("101 Pineapple Avenue Beachtown 98765", "+1 (555) 555-6789", State.HAWAII);


        User user1 = new User("Michael", "Jordan", "mick@gmail.com", "PassWord$1", "PassWord$1", admin, Gender.MALE, address1);
        User user2 = new User("Larry", "Bird", "larry@gmail.com", "PassWord$2", "PassWord$2", manager, Gender.MALE, address2);
        User user3 = new User("Angelina", "Sea", "star@yahoo.com", "PassWord$3", "PassWord$3", instructor, Gender.FEMALE, address3);
        User user4 = new User("Elizabeth", "Loren", "loren@hotmail.com", "PassWord$4", "PassWord$4", instructor, Gender.FEMALE, address4);
        User user5 = new User("Bill", "Wooden", "bill@gmail.com", "PassWord$5", "PassWord$5", instructor, Gender.MALE, address5);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);


        Course course1 = new Course(1L, "Spring Boot", "Spring Boot with MVC, ORM, REST", user2, LocalDate.of(2024, 4, 10), LocalDate.of(2024, 8, 18));
        Course course2 = new Course(2L, "Java Advanced", "Functional Programming, Streams, New Features", user2, LocalDate.of(2024, 2, 4), LocalDate.of(2024, 7, 18));
        Course course3 = new Course(3L, "React Spring Rest", "Create web app with React and Spring Rest", user2, LocalDate.of(2023, 12, 24), LocalDate.of(2024, 5, 8));

        courseService.save(course1);
        courseService.save(course2);
        courseService.save(course3);


        Student student1 = new Student("Igor", "Kotlin", "igor@yahoo.com", Gender.MALE, address6);
        Student student2 = new Student("Sue", "Jayden", "sue@gmail.com", Gender.FEMALE, address7);
        Student student3 = new Student("Tina", "Davis", "tina@gmail.com", Gender.FEMALE, address8);
        Student student4 = new Student("Jorge", "News", "news@gmail.com", Gender.MALE, address9);

        studentService.save(student1);
        studentService.save(student2);
        studentService.save(student3);
        studentService.save(student4);


        Instructor instructor1 = new Instructor("Elizabeth", "William", "beth@gmail.com");
        Instructor instructor2 = new Instructor("David", "Taylor", "David@gmail.com");
        Instructor instructor3 = new Instructor("Julia", "Jones", "Julia@gmail.com");

        instructorService.save(instructor1);
        instructorService.save(instructor2);
        instructorService.save(instructor3);


        Lesson lesson1 = new Lesson(1L, student2, "Dependency Injection", instructor1, course2);
        Lesson lesson2 = new Lesson(2L, student1, "Bean Annotation", instructor1, course2);
        Lesson lesson3 = new Lesson(3L, student4, "Lambda Expression", instructor1, course2);
        Lesson lesson4 = new Lesson(4L, student2, "Streams", instructor1, course2);
        Lesson lesson5 = new Lesson(5L, student3, "Spring Boot", instructor1, course2);
        Lesson lesson6 = new Lesson(6L, student1, "Core Java", instructor1, course2);

        lessonService.save(lesson1);
        lessonService.save(lesson2);
        lessonService.save(lesson3);
        lessonService.save(lesson4);
        lessonService.save(lesson5);
        lessonService.save(lesson6);


    }


}

