package com.sandystack.exp.rest;

import com.sandystack.exp.Exception.RestException;
import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Basic controller that returns serialized entity directly
 */
@RequestMapping("/secured")
@RestController()
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {

        if ("yyy".equals(id)) {
            throw RestException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("test message")
                    .details(List.of("message 1", "message 2"))
                    .build();
        }
        Optional<Employee> employeeOp = Optional.ofNullable(employeeService.fetchEmployeeById(id));

        return employeeOp
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/employees")
    ResponseEntity<List<Employee>> getEmployeeById() {
        List<Employee> employees = employeeService.findAll();
        return employees.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(employees);
    }
}
