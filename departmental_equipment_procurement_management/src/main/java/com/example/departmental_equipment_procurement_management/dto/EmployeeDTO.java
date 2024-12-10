package com.example.departmental_equipment_procurement_management.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private long empId;
    private String name;
    private String position;
    private String phoneNum;
    private String email;
    private String departmentName;
    // Tên phòng ban (nếu cần) (xây dựng giao diện chọn phòng ban dạng list, phats triển sau)
}

