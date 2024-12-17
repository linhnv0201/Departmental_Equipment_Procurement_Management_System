package com.example.departmental_equipment_procurement_management.service;


import com.example.departmental_equipment_procurement_management.dto.SupplierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.departmental_equipment_procurement_management.model.Supplier;
import com.example.departmental_equipment_procurement_management.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    //Get all supplier
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    //Get 1
    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

    // Thêm nhà cung cấp mới
    public Supplier addSupplier(SupplierDTO supplierDTO) {
        if (supplierRepository.existsBySupplierName(supplierDTO.getSupplierName())) {
            throw new IllegalArgumentException("Nhà cung cấp với tên này đã tồn tại.");
        }
        // Nếu không có nhà cung cấp trùng tên, tiến hành tạo và lưu nhà cung cấp mới
        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierDTO.getSupplierName());
        supplier.setContactInfo(supplierDTO.getContactInfo());
        supplier.setAddress(supplierDTO.getAddress());

        return supplierRepository.save(supplier);
    }

    // Cập nhật thông tin nhà cung cấp
    public Supplier updateSupplier(Integer supplierID, SupplierDTO supplierDTO) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(supplierID);
        if (existingSupplier.isPresent()) {
            Supplier supplier = existingSupplier.get();
            // Kiểm tra xem tên nhà cung cấp mới có bị trùng không
            if (supplierRepository.existsBySupplierName(supplierDTO.getSupplierName())) {
                throw new IllegalArgumentException("Nhà cung cấp với tên này đã tồn tại.");
            }

            supplier.setSupplierName(supplierDTO.getSupplierName());
            supplier.setContactInfo(supplierDTO.getContactInfo());
            supplier.setAddress(supplierDTO.getAddress());

            return supplierRepository.save(supplier);
        }
        throw new IllegalArgumentException("Nhà cung cấp không tồn tại.");
    }
    // Xóa nhà cung cấp
    public boolean deleteSupplier(Integer supplierID) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierID);
        if (supplier.isPresent()) {
            supplierRepository.delete(supplier.get());
            return true;  // Xóa thành công
        }
        return false;  // Không tìm thấy nhà cung cấp để xóa
    }
}
