package com.example.StudentCrud.controller;

import com.example.StudentCrud.model.Student;
import com.example.StudentCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    String viewHomePage(Model model){
        List<Student> list = studentService.listAll();
        model.addAttribute("listAll", list);
        return "index";
    }

    @GetMapping("/new")
    String add(Model model){
        model.addAttribute("student",new Student());
        return "new";
    }

    @PostMapping("/save")
    String save(@ModelAttribute("student") Student std){
        studentService.save(std);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    String deleteStudent(@PathVariable(value = "id") long id){
        studentService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    ModelAndView showEditStudentPage(@PathVariable(value = "id") long id){
        ModelAndView maw = new ModelAndView("new");
        Student std = studentService.get(id);
        maw.addObject("student",std);
        return maw;

    }

}
