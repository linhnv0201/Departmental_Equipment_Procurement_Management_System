package com.example.departmental_equipment_procurement_management.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEquipmentDTO {

    private int equipmentID; // Thiết bị
    private int quantity;    // Số lượng yêu cầu
}
