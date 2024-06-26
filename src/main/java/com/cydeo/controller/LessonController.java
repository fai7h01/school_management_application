package com.cydeo.controller;

import com.cydeo.entity.Lesson;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import com.cydeo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;
    private final UserService userService;
    private final StudentService studentService;

    public LessonController(LessonService lessonService, CourseService courseService, UserService userService, StudentService studentService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.userService = userService;
        this.studentService = studentService;
        }

    @GetMapping("/create")
    public String createLesson(Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("instructors", userService.findInstructor());
        model.addAttribute("lessons", lessonService.findAll());
        return "/lesson/lesson-create";
        }

    @PostMapping("/create")
    public String insertLesson(@Valid @ModelAttribute("lesson") Lesson lesson, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("instructors", userService.findInstructor());
            model.addAttribute("lessons", lessonService.findAll());
            return "/lesson/lesson-create";
            }
        lessonService.save(lesson);
        return "redirect:/lesson/create";
        }


    @GetMapping("/update/{id}")
    public String editLesson(@PathVariable("id") Long id, Model model) {
        model.addAttribute("lesson", lessonService.findById(id));
        model.addAttribute("instructors", userService.findInstructor());
        model.addAttribute("courses", courseService.findAll());
        return "/lesson/lesson-update";
        }

    @PostMapping("/update/{id}")
    public String updateLesson(@Valid @ModelAttribute Lesson lesson, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("instructors", userService.findInstructor());
            return "lesson/lesson-update";
            }
        lessonService.update(lesson);
        return "redirect:/lesson/create";
        }

    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable("id") Long id) {
        lessonService.deleteById(id);
        return "redirect:/lesson/create";
        }

    @ModelAttribute
    public void defineGeneralModels(Model model)
        {
        model.addAttribute("pageTitle", "Lesson || Events");
        }
    }


