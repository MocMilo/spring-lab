package com.sandystack.exp.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeDTO {

    private String firstName;

    private String lastName;

    // Assuming you might want to send just the department id instead of the entire department object
    private String departmentId;

    // You can add a list of sale ids if required
    // private List<String> saleIds;

    private LocalDateTime hiredate;

    private String title;

    private Integer salary;

    private String address;

    private String phone;

    private String email;

}
