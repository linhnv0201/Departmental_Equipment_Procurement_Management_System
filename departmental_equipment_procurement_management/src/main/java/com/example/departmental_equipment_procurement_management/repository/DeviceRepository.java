package com.example.departmental_equipment_procurement_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    // Có thể thêm các phương thức tùy chỉnh nếu cần
    // List<Device> findByDep_ID(int depId);
}

