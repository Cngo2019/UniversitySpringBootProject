package com.example.demo.Repository.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Professor;
import com.example.demo.Model.Course.Course;
import com.example.demo.Model.Course.CourseId;

import jakarta.transaction.Transactional;

@Transactional
public interface ProfessorDao extends JpaRepository<Professor, Integer> {

    @Query(value="SELECT * FROM professor WHERE professor_id =:id", nativeQuery = true)
    public Iterable<Professor> getProfessorById(int id);

    @Modifying
    @Query(value="UPDATE professor SET first_name=:#{#professor.firstName}, last_name=:#{#professor.lastName} WHERE professor_id=:id", nativeQuery = true)
    public void updateProfessorInformation(int id, @Param("professor") Professor newProfessor);

    @Modifying
    @Query(value="DELETE FROM professor WHERE professor_id=:id", nativeQuery = true)
    public void deleteProfessor(int id);

    @Query(value="SELECT p.* FROM professor_course pc "+ 
    "INNER JOIN professor p ON p.professor_id = pc.professor_id " + 
    "WHERE pc.course_prefix=:#{#course.coursePrefix} AND pc.course_number=:#{#course.courseNumber}", nativeQuery = true)
    public Iterable<Professor> getProfessorTeachesCourse(@Param("course") CourseId courseId);
}
