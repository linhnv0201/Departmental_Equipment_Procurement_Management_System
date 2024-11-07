package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Integer> {
    // Có thể thêm các phương thức tùy chỉnh nếu cần, ví dụ:
    // List<Device_Info> findByType(String type);
}
