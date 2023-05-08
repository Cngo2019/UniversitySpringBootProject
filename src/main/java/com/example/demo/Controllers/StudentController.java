package com.example.demo.Controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Student;
import com.example.demo.Repository.Dao.CourseDao;
import com.example.demo.Repository.Dao.ProfessorDao;
import com.example.demo.Repository.Dao.StudentDao;


@RestController
public class StudentController {

    @Autowired
    CourseDao courseRepository;
    @Autowired
    ProfessorDao professorRepository;
    @Autowired
    StudentDao studentRepository;
    
}
