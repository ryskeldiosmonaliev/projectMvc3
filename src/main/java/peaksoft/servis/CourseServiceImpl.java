package peaksoft.servis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.model.Course;
import peaksoft.model.Group;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    private final CourseDao courseDAO;

    @Autowired
    public CourseServiceImpl(CourseDao courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDAO.getAllCourse();
    }

    @Override
    public void addCourse(Course course,Long companyId) {
        courseDAO.addCourse(course,companyId);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseDAO.getCourseById(id);
    }

    @Override
    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
    }

    @Override
    public void deleteCourse(Course course) {
        courseDAO.deleteCourse(course);
    }

    @Override
    public List<Group> getGroupsByCourse(Long courseId) {
        return (List<Group>) courseDAO.getCourseById(courseId);
    }

}
