package com.sandystack.exp.repository;

import com.sandystack.exp.model.entities.Department;
import org.springframework.data.repository.CrudRepository;


public interface DepartmentRepository extends CrudRepository<Department, String> {
}
