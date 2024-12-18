package com.example.departmental_equipment_procurement_management.service;

import com.example.departmental_equipment_procurement_management.model.Department;
import com.example.departmental_equipment_procurement_management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Thêm phòng ban mới, kiểm tra trùng tên
    public Department addDepartment(Department department) {
        // Kiểm tra xem phòng ban đã tồn tại chưa
        if (departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
            throw new IllegalArgumentException("Phòng ban đã tồn tại");
        }

        // Nếu không trùng, tiến hành lưu phòng ban mới
        return departmentRepository.save(department);
    }

    // Cập nhật phòng ban, không cho phép trùng tên
    public Department updateDepartment(Integer departmentID, Department departmentDetails) {
        // Tìm phòng ban theo departmentID
        Optional<Department> department = departmentRepository.findById(departmentID);

        if (department.isPresent()) {
            Department existingDepartment = department.get();

            // Kiểm tra xem tên phòng ban mới đã tồn tại chưa
            if (!existingDepartment.getDepartmentName().equals(departmentDetails.getDepartmentName()) &&
                    departmentRepository.existsByDepartmentName(departmentDetails.getDepartmentName())) {
                throw new IllegalArgumentException("Tên phòng ban đã tồn tại");
            }

            // Cập nhật thông tin phòng ban
            existingDepartment.setDepartmentName(departmentDetails.getDepartmentName());
            existingDepartment.setBudget(departmentDetails.getBudget());

            return departmentRepository.save(existingDepartment);
        } else {
            throw new IllegalArgumentException("Không tìm thấy phòng ban để cập nhật");
        }
    }

    // Lấy danh sách tất cả phòng ban
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Lấy phòng ban theo ID
    public Optional<Department> getDepartmentById(Integer departmentID) {
        return departmentRepository.findById(departmentID);
    }

    // Xóa phòng ban
    public void deleteDepartment(Integer departmentID) {
        departmentRepository.deleteById(departmentID);
    }
}
