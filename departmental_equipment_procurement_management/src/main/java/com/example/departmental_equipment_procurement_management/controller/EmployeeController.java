package com.example.departmental_equipment_procurement_management.controller;

import com.example.departmental_equipment_procurement_management.dto.EmployeeDTO;
import com.example.departmental_equipment_procurement_management.model.Employee;
import com.example.departmental_equipment_procurement_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    // Thêm nhân viên mới
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(newEmployee);
    }

    // Cập nhật nhân viên
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int empId, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(empId, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Xóa nhân viên theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int empId) {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok("Nhân viên đã được xóa thành công.");
    }

    // Lấy tất cả nhân viên
    @GetMapping("/getall")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Lấy nhân viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(employee);
    }

    // Lấy danh sách nhân viên theo tên phòng ban
    @GetMapping("/department/{departmentID}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentID(@PathVariable int departmentID) {
        List<Employee> employees = employeeService.getEmployeesByDepartmentID(departmentID);
        return ResponseEntity.ok(employees);
    }
}

