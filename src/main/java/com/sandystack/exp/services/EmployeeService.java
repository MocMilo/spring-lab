package com.sandystack.exp.services;


import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Example of Service layer with Cache
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Cacheable(key = "#id", value = "Employee")
    public Employee fetchEmployeeById(String id) {

        System.out.println("fetchEmployee from DB");
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
