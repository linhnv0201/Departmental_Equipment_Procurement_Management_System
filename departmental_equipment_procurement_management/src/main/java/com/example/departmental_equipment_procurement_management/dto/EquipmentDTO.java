package com.example.departmental_equipment_procurement_management.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EquipmentDTO {
    private Integer equipmentID;
    @NotEmpty
    private String equipmentName;
    @NotEmpty
    private String equipmentDescription;
    @NotEmpty
    private Integer purchasePeriod;
    @NotEmpty
    private Integer supplierID;
    @NotEmpty
    private BigDecimal currentPrice;
}
