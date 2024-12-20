package com.example.departmental_equipment_procurement_management.service;

import com.example.departmental_equipment_procurement_management.dto.EmployeeDTO;
import com.example.departmental_equipment_procurement_management.model.Department;
import com.example.departmental_equipment_procurement_management.model.Employee;
import com.example.departmental_equipment_procurement_management.repository.DepartmentRepository;
import com.example.departmental_equipment_procurement_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    // Thêm nhân viên mới từ EmployeeDTO
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        // Kiểm tra trùng email
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // Lấy thông tin phòng ban từ tên phòng ban
        Department department = departmentRepository.findByDepartmentName(employeeDTO.getDepartmentName())
                .orElseThrow(() -> new IllegalArgumentException("Phòng ban không tồn tại"));

        // Tạo đối tượng Employee từ EmployeeDTO và gán thông tin
        Employee employee = new Employee();
        employee.setFullName(employeeDTO.getFullName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setPosition(employeeDTO.getPosition());
        employee.setDepartment(department);

        // Gán role tự động dựa trên phòng ban
//        employee.getRole(); // Phương thức gán role tự động

        return employeeRepository.save(employee);
    }


    // Cập nhật thông tin nhân viên
    public Employee updateEmployee(int empId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Nhân viên không tồn tại"));

        // Kiểm tra trùng email (ngoại trừ email của chính nhân viên đó)
        Optional<Employee> existingEmployeeWithEmail = Optional.ofNullable(employeeRepository.findByEmail(employeeDTO.getEmail()));
        if (existingEmployeeWithEmail.isPresent() && existingEmployeeWithEmail.get().getEmployeeID() != empId) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // Tìm và gán lại Department
        Department department = departmentRepository.findByDepartmentName(employeeDTO.getDepartmentName())
                .orElseThrow(() -> new IllegalArgumentException("Phòng ban không tồn tại"));

        // Cập nhật các trường cần thiết
        employee.setFullName(employeeDTO.getFullName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword()); // Mã hóa mật khẩu
        employee.setPosition(employeeDTO.getPosition());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    // Xóa nhân viên theo ID
    public void deleteEmployee(int empId) {
        if (!employeeRepository.existsById(empId)) {
            throw new IllegalArgumentException("Nhân viên không tồn tại");
        }
        employeeRepository.deleteById(empId);
    }

    // Lấy tất cả nhân viên
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Lấy nhân viên theo ID
    public Employee getEmployeeById(int empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Nhân viên không tồn tại"));
    }

    // Lấy nhân viên theo tên phòng ban
    public List<Employee> getEmployeesByDepartmentName(String departmentName) {
        return employeeRepository.findByDepartmentDepartmentName(departmentName);
    }

    // Lấy nhân viên theo id phòng ban
    public List<Employee> getEmployeesByDepartmentID(int departmentID) {
        return employeeRepository.findAllEmployeesByDepartmentID(departmentID);
    }

    //Lay nhan vien theo email
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
