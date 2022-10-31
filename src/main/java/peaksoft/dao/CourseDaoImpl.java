package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Transactional
@Repository
public class CourseDaoImpl implements CourseDao{
    @PersistenceContext
    private EntityManager entityManager;

    private final CompanyDao companyDAO;

    @Autowired
    public CourseDaoImpl(CompanyDao companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course>courses=entityManager.createQuery("from Course",Course.class).getResultList();
        Comparator<Course> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        courses.sort(comparator);
        return courses;
    }

    @Override
    public void addCourse(Course course,Long companyId) {
        Company company=companyDAO.getCompanyById(companyId);
        course.setCompany(company);
        entityManager.persist(course);
    }

    @Override
    public Course getCourseById(Long id) {
        Course course;
        course=entityManager.find(Course.class,id);
        return course;
    }

    @Override
    public void updateCourse(Course course, long id) {
        entityManager.merge(course);
    }

    @Override
    public void deleteCourse(Course course) {
        entityManager.merge(entityManager.contains(course)?course:entityManager.merge(course));

    }

}
