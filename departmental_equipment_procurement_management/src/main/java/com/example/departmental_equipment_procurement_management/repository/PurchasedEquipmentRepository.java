package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.PurchasedEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedEquipmentRepository extends JpaRepository<PurchasedEquipment, Integer> {
    //find all PE same department
    @Query("SELECT pe FROM PurchasedEquipment pe WHERE pe.department.departmentID = :departmentId")
    List<PurchasedEquipment> findByDepartmentId(int departmentId);

    //find all PE same request
    @Query("SELECT pe FROM PurchasedEquipment pe WHERE pe.request.requestID = :requestId")
    List<PurchasedEquipment> findByRequestID(int requestId);


}

