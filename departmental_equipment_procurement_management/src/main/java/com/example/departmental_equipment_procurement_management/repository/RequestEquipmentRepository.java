package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.RequestEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestEquipmentRepository extends JpaRepository<RequestEquipment, Integer> {
    //find all RE same request
    @Query("SELECT re FROM RequestEquipment re WHERE re.request.requestID = :requestId")
    List<RequestEquipment> findByRequestId(@Param("requestId") Integer requestId);
}


