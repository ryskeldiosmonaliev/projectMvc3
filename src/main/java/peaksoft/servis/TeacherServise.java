package peaksoft.servis;

import peaksoft.model.Teacher;

import java.util.List;

public interface TeacherServise {
    List<Teacher> getAllTeachers();
    void saveTeacher(Teacher teacher,Long id);
    Teacher getTeacherById(Long id);
    void deleteTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher,Long id);
}
