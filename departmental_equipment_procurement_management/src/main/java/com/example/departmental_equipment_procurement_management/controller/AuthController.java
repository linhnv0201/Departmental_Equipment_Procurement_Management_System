//package com.example.departmental_equipment_procurement_management.controller;
//
//import com.example.departmental_equipment_procurement_management.model.Employee;
//import com.example.departmental_equipment_procurement_management.repository.EmployeeRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final EmployeeRepository employeeRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthController(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
//        this.employeeRepository = employeeRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    // Đăng nhập (Spring Security sẽ tự động xử lý)
//    @PostMapping("/login")
//    public String login() {
//        return "Login successful!";
//    }
//}
