package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Professor;
import com.example.demo.Model.Course.Course;
import com.example.demo.Repository.Dao.CourseDao;
import com.example.demo.Repository.Dao.ProfessorDao;
import com.example.demo.Repository.Dao.StudentDao;

@RestController
public class ProfessorController {

    @Autowired
    CourseDao courseRepository;

    @Autowired
    ProfessorDao professorRepository;

    @GetMapping("/professors/{professor_id}")
    public Iterable<Professor> getProfessorById(@PathVariable("professor_id") int professor_id) {
        return professorRepository.getProfessorById(professor_id);
    }

    @PutMapping("professors/{id}")
    public Professor updateProfessorInformation(@PathVariable("id") int professor_id, @RequestBody Professor newProfessor) {
        professorRepository.updateProfessorInformation(professor_id, newProfessor);
        newProfessor.setId(professor_id);
        return newProfessor;
    }

    @DeleteMapping("professors/{id}")
    public String deleteProfessor(@PathVariable("id") int professor_id) {
        professorRepository.deleteProfessor(professor_id);
        return "OK";
    }

    @GetMapping("professors/{id}/courses")
    public Iterable<Course> getCoursesTaughtByProfessor(@PathVariable("id") int id) {
        return courseRepository.getCoursesTaughtByProfessor(id);
    }


}
