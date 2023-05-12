package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping(value="/courses/{coursePrefix}/{courseNumber}")
    public Course getFullCourse(@PathVariable("coursePrefix") String coursePrefix, @PathVariable("courseNumber") String courseNumber) {
        return courseRepository.getCourseById(new CourseId(coursePrefix, courseNumber));
    }

    @GetMapping(value="/courses/{coursePrefix}/{courseNumber}/professors")
    public Iterable<Professor> getProfessorWhoTeachesCourse(@PathVariable("coursePrefix") String coursePrefix, @PathVariable("courseNumber") String courseNumber) {
        CourseId courseId = new CourseId(coursePrefix, courseNumber);
        return professorRepository.getProfessorTeachesCourse(courseId);
    }

    @GetMapping(value="/courses")
    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(value="/courses/{coursePrefix}")
    public Iterable<Course> getCoursePrefix(@PathVariable("coursePrefix") String coursePrefix) {
        return courseRepository.getCoursesByPrefix(coursePrefix);
    }

    @PostMapping(value="/courses", consumes="application/json", produces="application/json")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping(
    value="/courses/{oldCoursePrefix}/{oldCourseNumber}",
    consumes="application/json", 
    produces="application/json")
    public Course updateCourse(@PathVariable("oldCoursePrefix") String oldCoursePrefix, @PathVariable("oldCourseNumber") String oldCourseNumber, @RequestBody Course newCourse) {
        courseRepository.updateCourse(new CourseId(oldCoursePrefix, oldCourseNumber), newCourse);
        return newCourse;
    }

    @DeleteMapping(
    value="/courses",
    consumes="application/json", 
    produces="application/json")
    public Course updateCourse(@RequestBody Course course) {
        courseRepository.deleteCourse(course.getCourseId());
        return course;
    }
    
}
