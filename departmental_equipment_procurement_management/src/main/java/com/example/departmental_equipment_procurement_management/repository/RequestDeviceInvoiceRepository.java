package com.example.departmental_equipment_procurement_management.repository;
import com.example.departmental_equipment_procurement_management.model.RequestDeviceInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDeviceInvoiceRepository extends JpaRepository<RequestDeviceInvoice, Integer> {
    // Có thể thêm các phương thức tùy chỉnh nếu cần
}

