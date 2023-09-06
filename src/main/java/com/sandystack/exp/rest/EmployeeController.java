package com.sandystack.exp.rest;

import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Basic controller that returns serialized entity directly
 */
@RestController
@AllArgsConstructor
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @GetMapping("/employee/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {

        return employeeRepository.findById(id)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/employees")
    ResponseEntity<List<Employee>> getEmployeeById() {
        List<Employee> employees =   employeeRepository.findAll();
        return employees.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(employees);
    }
}
