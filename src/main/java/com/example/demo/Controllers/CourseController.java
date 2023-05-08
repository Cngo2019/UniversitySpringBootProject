package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Professor;
import com.example.demo.Model.Course.Course;
import com.example.demo.Model.Course.CourseId;
import com.example.demo.Repository.Dao.CourseDao;
import com.example.demo.Repository.Dao.ProfessorDao;
import com.example.demo.Repository.Dao.StudentDao;

@RestController
public class CourseController {

    @Autowired
    CourseDao courseRepository;
    @Autowired
    ProfessorDao professorRepository;
    @Autowired
    StudentDao studentRepository;

    @GetMapping(value="/courses", params={"coursePrefix", "courseNumber"})
    public Course getFullCourse(@RequestParam String coursePrefix, @RequestParam String courseNumber) {
        CourseId courseId = new CourseId(coursePrefix, courseNumber);
        return courseRepository.getCourseById(courseId);
    }

    @GetMapping(value="/courses/professor", params={"coursePrefix", "courseNumber"})
    public Iterable<Professor> getProfessorWhoTeachesCourse(@RequestParam String coursePrefix, @RequestParam String courseNumber) {
        CourseId courseId = new CourseId(coursePrefix, courseNumber);
        return professorRepository.getProfessorTeachesCourse(courseId);
    }

    @GetMapping(value="/courses", params={"coursePrefix"})
    public Iterable<Course> getCoursePrefix(@RequestParam String coursePrefix) {
        return courseRepository.getCoursesByPrefix(coursePrefix);
    }

    @GetMapping(value="/courses")
    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping(value="/courses", consumes="application/json", produces="application/json")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }
    
}
