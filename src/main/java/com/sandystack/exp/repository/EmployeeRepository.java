package com.sandystack.exp.repository;

import com.sandystack.exp.model.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
