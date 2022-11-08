package peaksoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class TeacherDaoImpl  implements TeacherDao{
    @PersistenceContext
    EntityManager entityManager;

    private final CourseDao courseDAO;

    @Autowired
    public TeacherDaoImpl(CourseDao courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher>teachers=entityManager.createQuery("from Teacher",Teacher.class).getResultList();
        Comparator<Teacher> comparator=((o1, o2) -> (int) (o1.getId()-o2.getId()));
        teachers.sort(comparator);
        return teachers;
    }

    @Override
    public void saveTeacher(Teacher teacher,Long courseId) {
        Course course=courseDAO.getCourseById(courseId);
        teacher.setCourse(course);
        entityManager.persist(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class,id);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        entityManager.remove(entityManager.contains(teacher) ? teacher:entityManager.merge(teacher));

    }

    @Override
    public void updateTeacher(Teacher teacher, Long id) {
        Teacher teacher1=getTeacherById(id);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setLastName(teacher.getLastName());
        teacher1.setEmail(teacher.getEmail());
        entityManager.merge(teacher1);

    }
}
