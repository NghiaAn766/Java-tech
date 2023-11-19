package com.example.ex2.service;

import com.example.ex2.model.Employee;
import com.example.ex2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeManager implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long id) throws Exception {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employee not found"));
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }
}
