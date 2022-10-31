package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.servis.CourseService;
import peaksoft.servis.GroupService;
import peaksoft.servis.StudentService;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public GroupController(GroupService groupService, CourseService courseService, StudentService studentService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @ModelAttribute("courseList")
    public List<Course> findAllCourses() {
        return courseService.getAllCourse();
    }

    @GetMapping
    public String getAllGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "group/groups";

    }

    @GetMapping("/addGroup")
    public String addGroup(Model model) {
        model.addAttribute("group", new Group());
        return "group/addGroup";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.saveGroup(group, group.getCourseId());
        return "redirect:/groups";
    }

    @GetMapping("/{id}/update")
    public String updateCompany(Model model, @PathVariable("id") long id) {
        model.addAttribute("groupUpdate", groupService.getGroupById(id));
        return "group/updateGroup";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("groupUpdate") Group group,
                         @PathVariable("id") long id) {
        groupService.updateGroup(group, id);
        return "redirect:/groups";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") Long id) {
        Group group = groupService.getGroupById(id);
        groupService.deleteGroup(group);
        return "redirect:/groups";
    }
    @GetMapping("/search")
    public String search(String name,Model model){
        if (name!=null){
            model.addAttribute("list",studentService.findByStudentName(name));
        }
        else {
            model.addAttribute("list",studentService.getAllStudents());
        }
        return "/student/studentSearch";
    }
    @GetMapping("/courses/{groupId}")
    public String getCourseByGroupId(@PathVariable("groupId")Long groupId,Model model){
        List<Course>courses=groupService.getCoursesByGroup(groupId);
        model.addAttribute("courses",courses);
        return "group/courses";
    }
}
