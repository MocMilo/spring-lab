package com.sandystack.exp.repository;

import com.sandystack.exp.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepository extends JpaRepository<Department, String> {
}
