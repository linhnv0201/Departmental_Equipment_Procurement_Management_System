package com.example.departmental_equipment_procurement_management.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private long empId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String position;
    private String departmentName;
}

