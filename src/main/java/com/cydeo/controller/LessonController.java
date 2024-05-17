package com.cydeo.controller;

import com.cydeo.entity.Lesson;
import com.cydeo.service.LessonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @GetMapping("/update/{id}")
    public String editLesson(@PathVariable("id") String id, Model model) {
        model.addAttribute("lesson", lessonService.findById(id));
        model.addAttribute("description", lessonService.findById(id).getDescription());
        model.addAttribute("instructor", lessonService.findById(id).getInstructor());
        //model.addAttribute("course",lesson.getCourse());
        return "/lesson/lesson-update";
    }

    @PostMapping("/update")
    public String updateLesson(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.update(lesson);
        return "redirect:/lesson/update";
    }

   /*
    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable("id") String id)
        {
        lessonService.deleteById(id);
        return "redirect:/lesson/create";
        }
        */

}
