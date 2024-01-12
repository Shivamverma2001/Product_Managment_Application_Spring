package com.shivam.student.managment.service;

import com.shivam.student.managment.entity.Student;

import java.util.List;

public interface StudentService {
    public void add(Student student);
    public Student findById(int id);
    public List<Student> findAllStudent();
    public void deleteById(int id);

}
