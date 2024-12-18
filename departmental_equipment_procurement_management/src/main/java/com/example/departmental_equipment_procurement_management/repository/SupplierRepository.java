package com.example.departmental_equipment_procurement_management.repository;

import com.example.departmental_equipment_procurement_management.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    boolean existsBySupplierName(String supplierName);

}
