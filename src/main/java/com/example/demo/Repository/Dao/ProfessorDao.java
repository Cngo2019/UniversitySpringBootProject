package com.example.demo.Repository.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.Professor;
import com.example.demo.Model.Course.CourseId;

public interface ProfessorDao extends JpaRepository<Professor, Integer> {
    @Query(value="SELECT p.* FROM professor_course pc "+ 
    "INNER JOIN professor p ON p.professor_id = pc.professor_id " + 
    "WHERE pc.course_prefix=:#{#course.coursePrefix} AND pc.course_number=:#{#course.courseNumber}", nativeQuery = true)
    public Iterable<Professor> getProfessorTeachesCourse(@Param("course") CourseId courseId);
}
