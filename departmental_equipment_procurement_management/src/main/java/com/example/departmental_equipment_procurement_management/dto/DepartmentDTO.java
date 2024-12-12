package com.example.departmental_equipment_procurement_management.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Integer departmentID;
    @NotEmpty
    private String departmentName;
    @NotEmpty
    private Integer budget;
}