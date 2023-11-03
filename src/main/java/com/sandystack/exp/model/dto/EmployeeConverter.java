package com.sandystack.exp.model.dto;

import com.sandystack.exp.model.entities.Department;
import com.sandystack.exp.model.entities.Employee;
import com.sandystack.exp.repository.DepartmentRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeConverter {


    private DepartmentRepository departmentRepository;

    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());

        // Fetch the Department entity using the departmentId from the DTO
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElse(null);  // or throw an exception if not found
            employee.setDepartment(department);
        }

        employee.setHiredate(dto.getHiredate());
        employee.setTitle(dto.getTitle());
        employee.setSalary(dto.getSalary());
        employee.setAddress(dto.getAddress());
        employee.setPhone(dto.getPhone());
        employee.setEmail(dto.getEmail());

        return employee;
    }

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();

        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
        }
        dto.setHiredate(employee.getHiredate());
        dto.setTitle(employee.getTitle());
        dto.setSalary(employee.getSalary());
        dto.setAddress(employee.getAddress());
        dto.setPhone(employee.getPhone());
        dto.setEmail(employee.getEmail());

        return dto;
    }

}
