package com.example.ex2.controller;

import com.example.ex2.model.Employee;
import com.example.ex2.service.EmployeeManager;
import com.example.ex2.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeManager employeeManager;

    @GetMapping()
    RedirectView getIndex(){
        return new RedirectView("/employees");
    }

    @GetMapping(value = "/employees")
    String getEmployees(Model model){
        List<Employee> employeeList = (List<Employee>) employeeManager.getAllEmployees();
        model.addAttribute("employees", employeeList);
        return "index";
    }

    @GetMapping(value = "/employees/add")
    String addEmployee(){
        return "add";
    }

    @PostMapping("/employees/add")
    RedirectView add(HttpServletRequest request){
        Employee employee = new Employee(request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("address"),
                request.getParameter("phone"));
        employeeManager.save(employee);
        return new RedirectView("/employees");
    }

    @GetMapping(value = "employees/edit/{id}")
    String editEmployee(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) throws Exception{
        Employee employee = employeeManager.getEmployee(id);
        model.addAttribute("employee", employee);
        return "edit";
    }

    @PostMapping("/employees/edit/{id}")
    RedirectView updateEmployee(HttpServletRequest request,@PathVariable(value = "id") Long id) throws Exception{
        Employee employee = employeeManager.getEmployee(id);
        employee.setName(request.getParameter("name"));
        employee.setEmail(request.getParameter("email"));
        employee.setAddress(request.getParameter("address"));
        employee.setPhone(request.getParameter("phone"));
        employeeManager.save(employee);
        return new RedirectView("/employees");
    }

    @PostMapping("/employees/delete/{id}")
    RedirectView deleteEmployee(@PathVariable(value = "id") Long id) throws Exception{
        employeeManager.delete(id);
        return new RedirectView("/employees");
    }
}