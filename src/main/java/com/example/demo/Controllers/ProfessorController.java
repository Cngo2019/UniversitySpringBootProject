package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Professor;
import com.example.demo.Repository.Dao.CourseDao;
import com.example.demo.Repository.Dao.ProfessorDao;
import com.example.demo.Repository.Dao.StudentDao;

@RestController
public class ProfessorController {

    @Autowired
    CourseDao courseRepository;
    @Autowired
    ProfessorDao professorRepository;
    @Autowired
    StudentDao studentRepository;

}