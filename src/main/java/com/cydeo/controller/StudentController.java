package com.cydeo.controller;

import com.cydeo.entity.Student;
import com.cydeo.enums.State;
import com.cydeo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create")
    public String createUser(Model model){

        model.addAttribute("student",new Student());

       model.addAttribute("states", State.values());

        return "/student/student-create";
    }

    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("student")Student student, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){

            model.addAttribute("states", State.values());

            return "/student/student-create";
        }

        studentService.save(student);

        return "redirect:/student/create";
    }


}
