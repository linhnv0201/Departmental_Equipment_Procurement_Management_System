package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Integer> {
}
