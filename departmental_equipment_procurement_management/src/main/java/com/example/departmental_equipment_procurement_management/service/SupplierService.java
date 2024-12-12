package com.example.departmental_equipment_procurement_management.service;

import com.example.departmental_equipment_procurement_management.model.Supplier;
import com.example.departmental_equipment_procurement_management.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // Create (Thêm mới)
    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Read (Lấy danh sách tất cả nhà cung cấp)
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Read (Lấy nhà cung cấp theo ID)
    public Optional<Supplier> getSupplierById(Integer supplierID) {
        return supplierRepository.findById(supplierID);
    }

    // Update (Cập nhật nhà cung cấp)
    @Transactional
    public Supplier updateSupplier(Integer supplierID, Supplier supplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(supplierID);
        if (existingSupplier.isPresent()) {
            Supplier updatedSupplier = existingSupplier.get();
            updatedSupplier.setSupplierName(supplier.getSupplierName());
            updatedSupplier.setContactInfo(supplier.getContactInfo());
            updatedSupplier.setAddress(supplier.getAddress());
            return supplierRepository.save(updatedSupplier);
        }
        return null; // Nếu không tìm thấy nhà cung cấp
    }

    // Delete (Xóa nhà cung cấp)
    @Transactional
    public boolean deleteSupplier(Integer supplierID) {
        if (supplierRepository.existsById(supplierID)) {
            supplierRepository.deleteById(supplierID);
            return true;
        }
        return false; // Nếu không tìm thấy nhà cung cấp để xóa
    }
}

