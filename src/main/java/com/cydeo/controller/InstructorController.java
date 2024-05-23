package com.cydeo.controller;

import com.cydeo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    private final StudentService studentService;

    public InstructorController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/students")
    public String instructorPage(Model model){

        model.addAttribute("studentLessons", studentService.findStudentsByInstructor("loren@hotmail.com"));

        return "/instructor/general-assessment";
    }
    
}
