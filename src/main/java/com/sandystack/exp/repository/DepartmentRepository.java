package com.sandystack.exp.repository;

import com.sandystack.exp.model.Department;
import org.springframework.data.repository.CrudRepository;


public interface DepartmentRepository extends CrudRepository<Department, String> {
}
