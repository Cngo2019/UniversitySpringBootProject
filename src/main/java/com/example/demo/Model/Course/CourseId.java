package com.example.demo.Model.Course;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class CourseId implements Serializable {
    private String coursePrefix;

    private String courseNumber;

    public CourseId() {

    }

    public CourseId(String coursePrefix, String courseNumber) {
        this.coursePrefix = coursePrefix;
        this.courseNumber = courseNumber;
    }


    public int hashCode() {
        return coursePrefix.hashCode() + courseNumber.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof CourseId)) return false;
        if (obj == null) return false;
        CourseId pk = (CourseId) obj;
        return 
        this.getCourseNumber().equals(pk.getCourseNumber()) && this.getCoursePrefix().equals(pk.getCoursePrefix());
    }
}
