package com.sandystack.exp.services;


import com.sandystack.exp.aop.LogExecution;
import com.sandystack.exp.model.dto.EmployeeDTO;
import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Example of Service layer with Cache
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeRepository employeeRepository;

    @LogExecution
    //@Cacheable(key = "#id", value = "Employee")
    public Employee fetchEmployeeById(String id) {

        logger.info("Fetched Employee from DB");
        return employeeRepository.findById(id).orElse(null);
    }

    @LogExecution
    @Transactional
    public List<Employee> findAll() {
        logger.info("Fetched Employee from DB");
        return employeeRepository.findAll();
    }

    @Transactional
    public void save(Employee employee){

        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteById(String id){

        employeeRepository.deleteById(id);
    }

}
