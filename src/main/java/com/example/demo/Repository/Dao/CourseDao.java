package com.example.demo.Repository.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.Professor;
import com.example.demo.Model.Course.*;

public interface CourseDao extends JpaRepository<Course, CourseId>{

    @Query(value="SELECT * FROM course WHERE course.course_prefix=:prefixString", nativeQuery=true)
    public Iterable<Course> getCoursesByPrefix(String prefixString);
    
    @Query(value="SELECT * FROM course WHERE course.course_prefix=:#{#course.coursePrefix} AND course.course_number=:#{#course.courseNumber}", nativeQuery = true)
    public Course getCourseById(@Param("course") CourseId courseId);

    @Query(value="UPDATE course SET" +
    "course_prefix=:#{#newCourse.coursePrefix}, course_number=:#{#newCourse.courseNumber}"+ 
    "WHERE course_prefix=:#{#oldCourse.coursePrefix} AND course_number=:#{#oldCourse.courseNumber}", nativeQuery = true)
    public Course updateCourse(@Param("oldCourse") CourseId oldCourse, @Param("newCourse") CourseId newCourse);

}
