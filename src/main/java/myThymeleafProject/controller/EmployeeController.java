package myThymeleafProject.controller;

import myThymeleafProject.model.Employee;
import myThymeleafProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository1){
        employeeRepository=employeeRepository1;
    }

    @GetMapping("/list")
    public ModelAndView getAllEmployees(){
        ModelAndView mav=new ModelAndView("list-employee","workers",employeeRepository.findAll());
        return mav;
    }
    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployee(){
        ModelAndView mav=new ModelAndView("add-employee-form","employee",new Employee());

        return  mav;

    }
    @PostMapping("/saveEmployee ")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/list";
    }
}
