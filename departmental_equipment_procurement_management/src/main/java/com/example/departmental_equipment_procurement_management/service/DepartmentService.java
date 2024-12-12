package com.example.departmental_equipment_procurement_management.service;

import com.example.departmental_equipment_procurement_management.model.Department;
import com.example.departmental_equipment_procurement_management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Create (Thêm mới phòng ban)
    @Transactional
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Read (Lấy danh sách tất cả phòng ban)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Read (Lấy phòng ban theo ID)
    public Optional<Department> getDepartmentById(Integer departmentID) {
        return departmentRepository.findById(departmentID);
    }

    // Update (Cập nhật thông tin phòng ban)
    @Transactional
    public Department updateDepartment(Integer departmentID, Department department) {
        Optional<Department> existingDepartment = departmentRepository.findById(departmentID);
        if (existingDepartment.isPresent()) {
            Department updatedDepartment = existingDepartment.get();
            updatedDepartment.setDepartmentName(department.getDepartmentName());
            updatedDepartment.setBudget(department.getBudget());
            return departmentRepository.save(updatedDepartment);
        }
        return null; // Nếu không tìm thấy phòng ban
    }

    // Delete (Xóa phòng ban)
    @Transactional
    public boolean deleteDepartment(Integer departmentID) {
        Optional<Department> department = departmentRepository.findById(departmentID);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            return true;
        }
        return false; // Nếu không tìm thấy phòng ban
    }
}


