package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Có thể thêm các phương thức tùy chỉnh nếu cần
    // Ví dụ: tìm nhân viên theo tên hoặc vị trí
    // List<Employee> findByPosition(String position);
}

