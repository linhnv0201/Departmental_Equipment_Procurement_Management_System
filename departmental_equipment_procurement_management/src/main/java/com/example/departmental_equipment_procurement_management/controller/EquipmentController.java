package com.example.departmental_equipment_procurement_management.controller;

import com.example.departmental_equipment_procurement_management.dto.EquipmentDTO;
import com.example.departmental_equipment_procurement_management.model.Equipment;
import com.example.departmental_equipment_procurement_management.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;


    // Thêm mới thiết bị
    @PostMapping
    public ResponseEntity<Equipment> addEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        Equipment equipment = equipmentService.addEquipment(equipmentDTO);
        return new ResponseEntity<>(equipment, HttpStatus.CREATED);
    }

    // Cập nhật thiết bị
    @PutMapping("/{equipmentID}")
    public ResponseEntity<Equipment> updateEquipment(
            @PathVariable Integer equipmentID,
            @RequestBody EquipmentDTO equipmentDTO) {
        try {
            Equipment updatedEquipment = equipmentService.updateEquipment(equipmentID, equipmentDTO);
            return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa thiết bị
    @DeleteMapping("/{equipmentID}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Integer equipmentID) {
        try {
            equipmentService.deleteEquipment(equipmentID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Lấy danh sách thiết bị
    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        List<Equipment> equipments = equipmentService.getAllEquipments();
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }

    // Lay bang ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Equipment>> getEquipmentById(@PathVariable("id") int equipmentID) {
        Optional<Equipment> equipment = equipmentService.getEquipmentByID(equipmentID);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    // API để lấy danh sách thiết bị theo nhà cung cấp
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<Equipment>> getEquipmentBySupplier(@PathVariable Integer supplierId) {
        List<Equipment> equipments = equipmentService.getEquipmentBySupplier(supplierId);
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }
}
