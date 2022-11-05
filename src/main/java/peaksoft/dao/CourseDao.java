package peaksoft.dao;

import peaksoft.model.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourse();
    void addCourse(Course course,Long companyId);
    Course getCourseById(Long id);
    void updateCourse(Course course);
    void deleteCourse(Course course);
}
