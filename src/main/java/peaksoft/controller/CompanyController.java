package peaksoft.controller;

import peaksoft.model.Company;
import peaksoft.model.Student;
import peaksoft.servis.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.servis.StudentService;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final StudentService studentService;

    @Autowired
    public CompanyController(CompanyService companyService, StudentService studentService) {
        this.companyService = companyService;
        this.studentService = studentService;
    }

    @GetMapping()
    public String getAllCompanies(Model model){
        List<Company>companies=companyService.getAllCompanies();
        model.addAttribute("companies",companies);
        return "company/companies";
    }

    @GetMapping("/addCompany")
    public String addCompany(Model model){
        model.addAttribute("company",new Company());
        return "company/addCompany";
    }
    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company")Company company){
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/updateCompany/{id}")
    public String updateCompany(@PathVariable long id,Model model){
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company",company);
        return "company/updateCompany";
    }
    @PatchMapping("/companyUpdate")
    public String saveUpdateCompany(@ModelAttribute("company")Company company){
        companyService.updateCompany(company);
        return "redirect:/companies";
    }
    @DeleteMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable long id){
        companyService.deleteCompany(companyService.getCompanyById(id));
        return "redirect:/companies";
    }
    @GetMapping("/getStudents/{companyId}")
    public String getStudents(@PathVariable("companyId")Long companyId,Model model){
        List<Student>students;
        students= studentService.getStudentsByCompany(companyId);
        model.addAttribute("students",students);
        model.addAttribute("size",students.size());
        return "company/getStudents";
    }
}
