package myThymeleafProject.controller;

import myThymeleafProject.model.Employee;
import myThymeleafProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class EmployeeController {

    private EmployeeRepository empRepo;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        empRepo = employeeRepository;
    }

    @GetMapping({ "/list" })
    public ModelAndView getAllEmployees() {

        ModelAndView mav = new ModelAndView("list-employee", "calisanlar", empRepo.findAll());

        return mav;

    }


    @GetMapping("/addEmployeeForm") // 4)
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("add-employee-form", "employee", new Employee());

        return mav;
    }

    @PostMapping("/saveEmployee") //
    public String saveEmployee(@ModelAttribute Employee employee) {
        empRepo.save(employee);

        return "redirect:/list";
    }


    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId) {
        empRepo.deleteById(employeeId);

        return "redirect:/list";
    }

}