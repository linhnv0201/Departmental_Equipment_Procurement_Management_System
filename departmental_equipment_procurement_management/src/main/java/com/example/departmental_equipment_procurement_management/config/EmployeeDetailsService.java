package com.example.departmental_equipment_procurement_management.config;

import com.example.departmental_equipment_procurement_management.model.Employee;
import com.example.departmental_equipment_procurement_management.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@Data
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);

        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(employee.getRole()));

        return new org.springframework.security.core.userdetails.User(
                email,
                employee.getPassword(),
                authorities
        );

    }
}
