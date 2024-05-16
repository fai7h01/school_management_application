package com.cydeo.controller;

import com.cydeo.entity.Course;
import com.cydeo.service.CourseService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final UserService userService;
    private final CourseService courseService;

    public CourseController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/create")
    public String createCourse (Model model){
        model.addAttribute("course", new Course());
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("courses", courseService.findAll());
        return "/course/course-create";
    }

    @PostMapping("/create")
    public String insertCourse(@ModelAttribute("course") Course course){
        //before saving a new course, set all students with status "false" (as it is business requirements, set it in ServiceImp not in this Controller)
        courseService.save(course);
        return "redirect:/course/course-create";
    }

    @GetMapping("/update/{id}")//check @pathvariable - Can "courseId" after Long be acceptable?
    public String editCourse(@PathVariable ("id") Long id, Model model){
        model.addAttribute("course", courseService.findById(id));
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("courses", courseService.findAll());
        return "/course/course-update";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute("course") Course course){
        courseService.update(course);
        return "redirect:/course/course-create";
    }

    @GetMapping("/delete/{id}")//check @pathvariable - Can "courseId" after Long be acceptable?
    public String deleteCourse(@PathVariable("id") Long id){
        //before delete, there are some requirements which should be done under delete method in CourseServiceImpl!!!
        courseService.deleteById(id);
        return "redirect:/course/course-create";
    }


}

/*
CREATE:
- Assign new id to the course by UUID library.
- While new course is created, newly created course should assign to all students with status false.==> ENUMS for status??? Status as new variable for Course?
========
DELETE:
- Courses with lessons cannot be deleted. (In that case, the page provide this message “This Course has either one or more than one lessons. Not allowed to delete“.)
- If the course is successfully deleted, show “This Course is successfully deleted“ message in the page.
- Before deleting the course, remove this course from all students (courseStudent)
*/

