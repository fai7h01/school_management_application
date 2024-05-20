package com.cydeo.controller;

import com.cydeo.entity.Lesson;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lesson")
public class LessonController
    {
    private final LessonService lessonService;
    private final CourseService courseService;
    private final UserService userService;

    public LessonController(LessonService lessonService, CourseService courseService, UserService userService)
        {
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.userService = userService;
        }


    @GetMapping("/update/{id}")
    public String editLesson(@PathVariable("id") String id, Model model)
        {
        model.addAttribute("lesson",lessonService.findById(id));
        model.addAttribute("description",lessonService.findById(id).getDescription());
        model.addAttribute("instructor",userService.findInstructor());
        model.addAttribute("course",courseService.findAll());
        return "/lesson/lesson-update";
        }

    @PostMapping("/update")
    public String updateLesson(@Valid @ModelAttribute("lesson") Lesson lesson)
        {
        lessonService.update(lesson);
        return "redirect:/lesson/update";
        }

    }
