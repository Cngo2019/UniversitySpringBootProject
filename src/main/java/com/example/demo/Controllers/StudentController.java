package com.example.demo.Controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Student;
import com.example.demo.Repository.Dao.CourseDao;
import com.example.demo.Repository.Dao.ProfessorDao;
import com.example.demo.Repository.Dao.StudentDao;

import jakarta.persistence.Id;


@RestController
public class StudentController {

    @Autowired
    CourseDao courseRepository;
    @Autowired
    ProfessorDao professorRepository;
    @Autowired
    StudentDao studentRepository;

    @GetMapping("/students/{id}")
    public Iterable<Student> getStudentById(@PathVariable("id") int student_id) {
        return studentRepository.getStudentById(student_id);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentRepository.deleteStudent(id);
        return "OK";
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable("id") int id, Student newStudent) {
        studentRepository.updateStudentInformation(id, newStudent);
        newStudent.setId(id);
        return newStudent;
    }
}
