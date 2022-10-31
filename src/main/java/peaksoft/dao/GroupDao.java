package peaksoft.dao;

import peaksoft.model.Course;
import peaksoft.model.Group;

import java.util.List;

public interface GroupDao {
    List<Group> getAllGroups();
    void saveGroup(Group group, Long coursesId);
    Group getGroupById(Long id);
    void deleteGroup(Group group);
    void updateGroup(Group group,Long id);
    List<Course>getCoursesByGroup(Long groupId);
}
