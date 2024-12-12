package com.example.departmental_equipment_procurement_management.service;

import com.example.departmental_equipment_procurement_management.model.Employee;
import com.example.departmental_equipment_procurement_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create (Thêm mới nhân viên)
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Read (Lấy danh sách tất cả nhân viên)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Read (Lấy nhân viên theo ID)
    public Optional<Employee> getEmployeeById(Integer employeeID) {
        return employeeRepository.findById(employeeID);
    }

    // Read (Lấy nhân viên theo DepartmentID)
    public List<Employee> getEmployeesByDepartmentId(int id) {
        return employeeRepository.findByDepartmentID(id);
    }

    @Transactional
    public Employee updateEmployee(Integer employeeID, Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeID);
        if (existingEmployee.isPresent()) {
            Employee updatedEmployee = existingEmployee.get();

            updatedEmployee.setFullName(employee.getFullName());
            updatedEmployee.setEmail(employee.getEmail());
            updatedEmployee.setPosition(employee.getPosition());
            // Cập nhật thông tin phòng ban qua đối tượng Department
            updatedEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(updatedEmployee);
        }
        return null; // Nếu không tìm thấy nhân viên
    }


    // Delete (Xóa nhân viên)
    @Transactional
    public boolean deleteEmployee(Integer employeeID) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeID);
        if (existingEmployee.isPresent()) {
            employeeRepository.delete(existingEmployee.get());
            return true;
        }
        return false; // Nếu không tìm thấy nhân viên
    }
}
