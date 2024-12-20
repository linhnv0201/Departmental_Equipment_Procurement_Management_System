package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.Department;
import com.example.departmental_equipment_procurement_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmail(String email);  // Custom query to find user by email

    boolean existsByEmail(String email);
    // Phương thức tìm kiếm nhân viên theo department

    // Sử dụng JPQL để truy vấn theo departmentID
    @Query("SELECT e FROM Employee e WHERE e.department.departmentID = :departmentid")
    List<Employee> findAllEmployeesByDepartmentID(@Param("departmentid") int departmentid);

    List<Employee> findByDepartmentDepartmentName(String departmentName);


}

