package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.servis.CompanyService;
import peaksoft.servis.CourseService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseControll {
    private final CompanyService companyService;
    private final CourseService coursesService;
@Autowired
    public CourseControll(CompanyService companyService, CourseService coursesService) {
        this.companyService = companyService;
        this.coursesService = coursesService;
    }


    @ModelAttribute("companyList")
    public List<Company>getAllCompany(){
        return companyService.getAllCompanies();
    }


    @GetMapping()
    public String getAllCourses( Model model){
        model.addAttribute("courses",coursesService.getAllCourse());
        return "course/courses";

    }

    @GetMapping("/addCourse")
    public String addCourse(Model model){
        model.addAttribute("course",new Course());
        return "course/addCourse";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course){
        coursesService.addCourse(course,course.getCompanyId());
        return "redirect:/courses";
    }

    @GetMapping("/{id}/updateCourse")
    public String updateCourse(@PathVariable("id") Long id, Model model){
        Course course =  coursesService.getCourseById(id);
        model.addAttribute("course", course);
        return "course/updateCourse";
    }

    @PatchMapping ("/{id}")
    public String saveUpdateCourse(@PathVariable("id") Long id,@ModelAttribute("course") Course course){
        coursesService.updateCourse(course,id);
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id")Long id){
        coursesService.deleteCourse(coursesService.getCourseById(id));
        return "redirect:/courses";
    }
    @GetMapping("/groups/{courseId}")
    public String getCourses(@PathVariable("courseId")Long courseId,Model model){
        List<Group>groups;
        groups=coursesService.getGroupsByCourse(courseId);
        model.addAttribute("groups",groups);
        return "course/getGroups";
    }

}
