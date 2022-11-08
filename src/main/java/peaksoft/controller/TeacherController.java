package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Teacher;
import peaksoft.servis.CourseService;
import peaksoft.servis.TeacherServise;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherServise teacherService;
    private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherServise teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @ModelAttribute("teacherList")
    public List<Teacher> findAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @ModelAttribute("courseList")
    public List<Course> findAllCourses(){
        return courseService.getAllCourse();
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("teachers",teacherService.getAllTeachers());
        return "teacher/teachers";
    }

    @GetMapping("/addTeacher")
    public String saveTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/addTeacher";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher,teacher.getCourseId());
        return "redirect:/teachers";
    }


    @GetMapping("/{id}/update")
    public String updateTeacher(Model model, @PathVariable("id") long id) {
        model.addAttribute("teacherUpdate", teacherService.getTeacherById(id));
        return "teacher/updateTeacher";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("teacherUpdate") Teacher teacher,
                         @PathVariable("id") long id) {
        teacherService.updateTeacher(teacher, id);
        return "redirect:/teachers";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        System.out.println("teacher before delete " + teacherService.getTeacherById(id).getEmail());
        teacherService.deleteTeacher(teacherService.getTeacherById(id));
        System.out.println("teacher after delete " + teacherService.getTeacherById(id).getEmail());
        return "redirect:/teachers";
    }
}
