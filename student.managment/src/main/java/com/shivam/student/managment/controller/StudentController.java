package com.shivam.student.managment.controller;

import com.shivam.student.managment.entity.Student;
import com.shivam.student.managment.service.StudentService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @RequestMapping("/students/new")
    public String addStudent(Model model){
        Student student=new Student();
        model.addAttribute("student",student);
        return "add";
    }
    @PostMapping("/students/new/process")
    public String addStudentProcess(@ModelAttribute("student") Student student){
        studentService.add(student);
        return "redirect:/students";
    }
    @GetMapping("/students")
    public String showStudents(Model model){
        List<Student> students=studentService.findAllStudent();
        model.addAttribute("students", students);
        return "home";
    }
    @GetMapping("/students/{id}/update")
    public String editStudent(@PathVariable("id") Integer id,Model model){
        Student student=studentService.findById(id);
        model.addAttribute("student",student);
        return "update";
    }
    @PostMapping("/studentUpdate/{id}")
    public String editStudentProcess(@PathVariable("id") Integer id, @ModelAttribute("student") Student student) {
        // Assuming you want to update the existing student with the given ID
        Student existingStudent = studentService.findById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // Save the updated student
        studentService.add(existingStudent);

        return "redirect:/students";
    }
    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Integer id){
        studentService.deleteById(id);
        return "redirect:/students";
    }

}
