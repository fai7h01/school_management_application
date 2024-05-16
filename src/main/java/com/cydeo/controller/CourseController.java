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
    public String createCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("courses", courseService.findAll());
        return "/course/course-create";
    }

    @PostMapping("/create")
    public String insertCourse(@ModelAttribute("course") Course course) {
        //before saving a new course, set all students with status "false" (as it is business requirements, set it in ServiceImp not in this Controller)
        courseService.save(course);
        return "redirect:/course/course-create";
    }

    //update

    //delete

}



