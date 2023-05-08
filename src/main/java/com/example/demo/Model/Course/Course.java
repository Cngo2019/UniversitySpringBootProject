package com.example.demo.Model.Course;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data 
@Entity
@Table(name="Course")
public class Course {
    @EmbeddedId
    private CourseId courseId;
}
