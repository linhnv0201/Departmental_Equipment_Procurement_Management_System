package com.example.departmental_equipment_procurement_management.controller;

import com.example.departmental_equipment_procurement_management.model.Supplier;
import com.example.departmental_equipment_procurement_management.service.SupplierService;
import com.example.departmental_equipment_procurement_management.dto.SupplierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    //Get all supplier
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    // Lấy thông tin nhà cung cấp theo ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getSupplierById(@PathVariable("id") Integer id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.status(404).body("Không tìm thấy nhà cung cấp");        }
    }

    // Thêm nhà cung cấp mới
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Supplier> addSupplier(@RequestBody SupplierDTO supplierDTO) {
        Supplier newSupplier = supplierService.addSupplier(supplierDTO);
        return ResponseEntity.ok(newSupplier);  // Trả về nhà cung cấp vừa được thêm
    }

    // Cập nhật thông tin nhà cung cấp
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        Supplier updatedSupplier = supplierService.updateSupplier(id, supplierDTO);
        if (updatedSupplier != null) {
            return ResponseEntity.ok(updatedSupplier);  // Trả về nhà cung cấp đã cập nhật
        } else {
            return ResponseEntity.notFound().build();  // Không tìm thấy nhà cung cấp
        }
    }

    // Xóa nhà cung cấp
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteSupplier(@PathVariable Integer id) {
        boolean isDeleted = supplierService.deleteSupplier(id);
        if (isDeleted) {
            return ResponseEntity.ok("Nhà cung cấp đã được xóa thành công");
        } else {
            return ResponseEntity.status(404).body("Không tìm thấy nhà cung cấp để xóa");
        }
    }
}
