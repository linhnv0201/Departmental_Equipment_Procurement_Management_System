package com.example.departmental_equipment_procurement_management.service;

import com.example.departmental_equipment_procurement_management.dto.EquipmentDTO;
import com.example.departmental_equipment_procurement_management.model.Department;
import com.example.departmental_equipment_procurement_management.model.Equipment;
import com.example.departmental_equipment_procurement_management.model.Supplier;
import com.example.departmental_equipment_procurement_management.repository.EquipmentRepository;
import com.example.departmental_equipment_procurement_management.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    // Thêm mới thiết bị
    @Transactional
    public Equipment addEquipment(EquipmentDTO equipmentDTO) {
        // Lấy thông tin phòng ban từ tên phòng ban
        Supplier supplier = supplierRepository.findById(equipmentDTO.getSupplierID())
                .orElseThrow(() -> new IllegalArgumentException("Nhà cung cấp không tồn tại"));

        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentDTO.getEquipmentName());
        equipment.setEquipmentDescription(equipmentDTO.getEquipmentDescription());
        equipment.setPurchasePeriod(equipmentDTO.getPurchasePeriod());
        equipment.setCurrentPrice(equipmentDTO.getCurrentPrice().doubleValue());
        equipment.setSupplier(supplier);

        return equipmentRepository.save(equipment);
    }

    // Cập nhật thiết bị
    @Transactional
    public Equipment updateEquipment(Integer equipmentID, EquipmentDTO equipmentDTO) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(equipmentID);
        if (optionalEquipment.isPresent()) {
            Supplier supplier = supplierRepository.findById(equipmentDTO.getSupplierID())
                    .orElseThrow(() -> new IllegalArgumentException("Nhà cung cấp không tồn tại"));
            Equipment equipment = optionalEquipment.get();
            equipment.setEquipmentName(equipmentDTO.getEquipmentName());
            equipment.setEquipmentDescription(equipmentDTO.getEquipmentDescription());
            equipment.setPurchasePeriod(equipmentDTO.getPurchasePeriod());
            equipment.setCurrentPrice(equipmentDTO.getCurrentPrice().doubleValue());
            equipment.setSupplier(supplier);
            return equipmentRepository.save(equipment);
        } else {
            throw new RuntimeException("Equipment not found with ID: " + equipmentID);
        }
    }

    // Xóa thiết bị
    @Transactional
    public void deleteEquipment(Integer equipmentID) {
        if (equipmentRepository.existsById(equipmentID)) {
            equipmentRepository.deleteById(equipmentID);
        } else {
            throw new RuntimeException("Equipment not found with ID: " + equipmentID);
        }
    }

    // Lấy danh sách thiết bị
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    // Tìm thiết bị theo id
    public Optional<Equipment> getEquipmentByID(int id) {
        return equipmentRepository.findById(id);
    }

    public List<Equipment> getEquipmentBySupplier(Integer supplierId) {
        // Tìm nhà cung cấp theo supplierId
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found with id " + supplierId));

        // Lấy danh sách thiết bị từ nhà cung cấp
        return equipmentRepository.findBySupplier(supplier);
    }
}
