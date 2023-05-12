package com.example.demo.Repository.Dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Professor;
import com.example.demo.Model.Student;
import com.example.demo.Model.Course.CourseId;

import jakarta.transaction.Transactional;

/**
 * Need to actually test all these methods/endpoints to see if they work.
 */
@Transactional
public interface StudentDao extends JpaRepository<Student, Integer> {
    @Query(value="SELECT * FROM student WHERE student_id =:id", nativeQuery = true)
    public Iterable<Student> getStudentById(int id);

    @Modifying
    @Query(value="UPDATE student SET first_name=:#{#student.firstName}, last_name=:#{#student.lastName} WHERE student_id=:id", nativeQuery = true)
    public void updateStudentInformation(int id, @Param("student") Student newStudent);

    @Modifying
    @Query(value="DELETE FROM student WHERE student_id=:id", nativeQuery = true)
    public void deleteStudent(int id);

}
