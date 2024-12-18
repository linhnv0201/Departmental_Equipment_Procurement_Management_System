package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByDepartmentName(String departmentName);  // Tìm phòng ban theo tên
    boolean existsByDepartmentName(String departmentName);

}

