package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Student;
import peaksoft.servis.GroupService;
import peaksoft.servis.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService,GroupService groupService) {
        this.studentService = studentService;
        this.groupService=groupService;
    }

    @ModelAttribute
    public void getAllGroups(Model model){
        model.addAttribute("listGroups",groupService.getAllGroups());

    }
    @GetMapping
    public String getAllStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "student/students";
    }
    @GetMapping("/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "student/addStudent";
    }
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student")Student student){
        studentService.saveStudent(student,student.getGroupId());
        return "redirect:/students";
    }
    @GetMapping("/{id}/updateStudent")
    public String updateStudent(@PathVariable("id")Long id, Model model){
        model.addAttribute("updateStudent",studentService.getStudentById(id));
        return "student/updateStudent";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id")Long id,@ModelAttribute("updateStudent")Student student){
        studentService.updateStudent(student,id);
        return "redirect:/students";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        studentService.deleteStudent(studentService.getStudentById(id));
        return "redirect:/students";
    }
}
