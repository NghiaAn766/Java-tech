package com.example.ex2.service;

import com.example.ex2.model.Employee;

public interface EmployeeService {
    Iterable<Employee> getAllEmployees();

    Employee getEmployee(long id) throws Exception;

    void save(Employee employee);

    void delete(long id);
}
