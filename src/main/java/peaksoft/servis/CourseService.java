package peaksoft.servis;

import peaksoft.model.Course;
import peaksoft.model.Group;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();

    void addCourse(Course course, Long companyId);
    Course getCourseById(Long id);
    void updateCourse(Course course, long id);
    void deleteCourse(Course course);
    List<Group>getGroupsByCourse(Long courseId);
}
