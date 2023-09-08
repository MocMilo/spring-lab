package com.sandystack.exp.services;


import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Example of Service layer with Cache
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeRepository employeeRepository;

    @Cacheable(key = "#id", value = "Employee")
    public Employee fetchEmployeeById(String id) {

        System.out.println("fetchEmployee from DB");
        logger.info("fetch Employee from DB");
        logger.error("fetch Employee from DB");
        logger.trace("fetch Employee from DB");
        logger.debug("fetch Employee from DB");
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> findAll() {
        System.out.println("fetchEmployee from DB");
        logger.info("fetch Employee from DB");
        logger.error("fetch Employee from DB");
        logger.trace("fetch Employee from DB");
        logger.debug("fetch Employee from DB");

        return employeeRepository.findAll();
    }
}
