package com.cydeo.controller;

import com.cydeo.entity.Course;
import com.cydeo.service.CourseService;
import com.cydeo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public String insertCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("managers", userService.findManagers());
            model.addAttribute("courses", courseService.findAll());
            return "/course/course-create";
        }
        //before saving a new course, set all students with status "false" (as it is business requirements, set it in ServiceImp not in this Controller)

        courseService.save(course);
        return "redirect:/course/create";
    }


    //update

    @GetMapping("/update/{id}")
    public String editCourse(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.findById(id));
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("courses", courseService.findAll());
        return "/course/course-update";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("managers", userService.findManagers());
            model.addAttribute("courses", courseService.findAll());
            return "/course/course-update";
        }
        courseService.update(course);
        return "redirect:/course/create";
    }

    //delete

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        if (courseService.isNotEligibleToDelete(id)) {
            redirectAttributes.addFlashAttribute("error", "This Course has either one or more than one lessons. Not allowed to delete");
        } else {
            redirectAttributes.addFlashAttribute("success", "This Course is successfully deleted");
            courseService.deleteById(id);
        }
        return "redirect:/course/create";
    }

}

