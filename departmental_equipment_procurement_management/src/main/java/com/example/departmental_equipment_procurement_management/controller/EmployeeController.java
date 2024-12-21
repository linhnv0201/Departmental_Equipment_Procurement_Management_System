package com.example.departmental_equipment_procurement_management.controller;

import com.example.departmental_equipment_procurement_management.dto.EmployeeDTO;
import com.example.departmental_equipment_procurement_management.model.Department;
import com.example.departmental_equipment_procurement_management.model.Employee;
import com.example.departmental_equipment_procurement_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Lấy tất cả nhân viên
    @GetMapping("/getall")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Lấy nhân viên theo ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(employee);
    }

    // Thêm nhân viên mới
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(newEmployee);
    }

    // Cập nhật nhân viên
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int empId, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(empId, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Nhân viên tự cập nhật nhân viên
    @PutMapping("/currentuser")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeService.findEmployeeByEmail(email);
        Employee updatedEmployee = employeeService.updateEmployee(employee.getEmployeeID(), employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Xóa nhân viên theo ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int empId) {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok("Nhân viên đã được xóa thành công.");
    }

    // Lấy người đang đăng nhập
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Employee> getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeService.findEmployeeByEmail(email);
        return ResponseEntity.ok(employee);
    }

    // Lấy phòng của người đang đăng nhập
    @GetMapping("/user/department")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Department> getUserDepartment() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Department department = employeeService.findDepartmentByEmail(email);
        return ResponseEntity.ok(department);
    }

    // Lấy danh sách nhân viên theo tên phòng ban
    @GetMapping("/department/{departmentID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentID(@PathVariable int departmentID) {
        List<Employee> employees = employeeService.getEmployeesByDepartmentID(departmentID);
        return ResponseEntity.ok(employees);
    }
}

