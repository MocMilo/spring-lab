package com.sandystack.exp.rest;

import com.sandystack.exp.Exception.RestException;
import com.sandystack.exp.model.dto.EmployeeConverter;
import com.sandystack.exp.model.dto.EmployeeDTO;
import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.services.EmployeeService;
import com.sandystack.exp.services.concurrent.ProxyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Basic controller that returns serialized entity directly
 */
@RequestMapping("/secured")
@RestController()
@AllArgsConstructor
public class EmployeeController {

    private EmployeeConverter employeeConverter;

    private EmployeeService employeeService;

    private ProxyService proxyService;

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

    @PostMapping("/employee")
    void createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        employeeService.save(employeeConverter.toEntity(employeeDTO));
    }

    @PutMapping("/employee")
    void updateEmployee(@RequestBody EmployeeDTO employeeDTO) {

        employeeService.save(employeeConverter.toEntity(employeeDTO));
    }

    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable String id) {

        employeeService.deleteById(id);
    }

    @PostMapping("/experiment/{expNo}/Employee")
    void updateEmployee(@PathVariable Integer expNo) {

        if (expNo == 1) {
            // optimistic lock exception
            proxyService.saveWithOptimisticLockException();
        } else {
            throw RestException.builder()
                    .message("No such experiment, provide correct number param")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}
