package com.example.departmental_equipment_procurement_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDTO {
    private int employeeID; // Tên người tạo yêu cầu
    private String description;
    private List<RequestEquipmentDTO> requestEquipments; // Danh sách thiết bị yêu cầu
}
