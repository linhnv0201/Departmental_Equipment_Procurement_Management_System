//package com.example.departmental_equipment_procurement_management.security;
//
//import com.example.departmental_equipment_procurement_management.model.Employee;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import java.util.Collections;
//
//
//public class CustomUserDetails extends User {
//
//    private final Employee employee;
//
//    public CustomUserDetails(Employee employee) {
//        super(employee.getEmail(), employee.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + employee.getRole())));
//        this.employee = employee;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//}
