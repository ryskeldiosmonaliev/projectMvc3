package peaksoft.servis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.TeacherDao;
import peaksoft.model.Teacher;

import java.util.List;

@Service
public class TeacherServiseImpl implements TeacherServise {
    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiseImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    @Override
    public void saveTeacher(Teacher teacher,Long id) {
        teacherDao.saveTeacher(teacher,id);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherDao.getTeacherById(id);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherDao.deleteTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher, Long id) {
        teacherDao.updateTeacher(teacher,id);
    }
}
