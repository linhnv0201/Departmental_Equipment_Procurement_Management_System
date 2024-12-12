package com.example.departmental_equipment_procurement_management.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SupplierDTO {
    private Integer supplierID;
    @NotEmpty
    private String supplierName;
    @NotEmpty
    private String contactInfo;
    @NotEmpty
    private String address;
}