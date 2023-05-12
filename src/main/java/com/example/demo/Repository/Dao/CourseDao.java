package com.example.demo.Repository.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Professor;
import com.example.demo.Model.Course.*;

import jakarta.transaction.Transactional;

@Transactional
public interface CourseDao extends JpaRepository<Course, CourseId>{

    @Query(value="SELECT * FROM course WHERE course.course_prefix=:prefixString", nativeQuery=true)
    public Iterable<Course> getCoursesByPrefix(String prefixString);
    
    @Query(value="SELECT * FROM course WHERE course.course_prefix=:#{#courseId.coursePrefix} AND course.course_number=:#{#courseId.courseNumber}", nativeQuery = true)
    public Course getCourseById(@Param("courseId") CourseId courseId);

    // Update needs the old course ID and then what the new state is going to be
    @Modifying
    @Query(value="UPDATE course SET " +
    "course_prefix=:#{#newCourse.courseId.coursePrefix}, course_number=:#{#newCourse.courseId.courseNumber}, course_description=:#{#newCourse.courseDescription} "+ 
    "WHERE course_prefix=:#{#courseId.coursePrefix} AND course_number=:#{#courseId.courseNumber}", nativeQuery = true)
    public void updateCourse(@Param("courseId") CourseId oldCourse, @Param("newCourse") Course newCourse);

    @Modifying
    @Query(value= "DELETE FROM course WHERE course_prefix=:#{#courseId.coursePrefix} AND course_number =:#{#courseId.courseNumber}", nativeQuery = true)
    public void deleteCourse(@Param("courseId") CourseId courseId);

    @Query(value="SELECT c.* FROM professor_course " +
    "INNER JOIN course c ON " +
    "c.course_prefix = professor_course.course_prefix AND c.course_number = professor_course.course_number " +
    "WHERE professor_id = :id", nativeQuery = true)
    public Iterable<Course> getCoursesTaughtByProfessor(int id);

}
