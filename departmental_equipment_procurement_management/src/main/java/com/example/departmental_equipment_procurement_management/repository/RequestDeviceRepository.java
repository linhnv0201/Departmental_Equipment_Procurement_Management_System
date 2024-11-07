package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.RequestDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDeviceRepository extends JpaRepository<RequestDevice, Integer> {
    // Có thể thêm các phương thức tùy chỉnh nếu cần
}

