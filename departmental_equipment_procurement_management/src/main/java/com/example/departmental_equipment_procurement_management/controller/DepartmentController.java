package com.example.departmental_equipment_procurement_management.controller;

import com.example.departmental_equipment_procurement_management.model.Department;
import com.example.departmental_equipment_procurement_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Lấy danh sách tất cả phòng ban
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    // Lấy thông tin phòng ban theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Integer id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Thêm mới phòng ban
    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.addDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    // Cập nhật phòng ban
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable("id") Integer id,
            @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    // Xóa phòng ban
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
