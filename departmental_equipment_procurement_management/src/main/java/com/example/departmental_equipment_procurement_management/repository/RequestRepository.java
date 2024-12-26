package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.Request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    //find all request of a employee
    @Query("SELECT re FROM Request re WHERE re.employee.employeeID = :employeeid")
    List<Request> findAllRequestOfAnEmployee(@Param("employeeid") Integer employeeid);

    //find all request of a department
    @Query("SELECT re FROM Request re WHERE re.department.departmentID = :departmentid")
    List<Request> findAllRequestOfAnDepartment(@Param("departmentid") Integer departmentid);

}