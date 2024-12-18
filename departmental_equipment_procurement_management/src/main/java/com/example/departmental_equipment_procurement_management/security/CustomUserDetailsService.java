//package com.example.departmental_equipment_procurement_management.security;
//
//import com.example.departmental_equipment_procurement_management.model.Employee;
//import com.example.departmental_equipment_procurement_management.repository.EmployeeRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final EmployeeRepository employeeRepository;
//
//    public CustomUserDetailsService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeRepository.findByEmail(username));
//
//        if (optionalEmployee.isPresent()) {
//            Employee employee = optionalEmployee.get();
//            return new CustomUserDetails(employee);
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
//
//
//}
