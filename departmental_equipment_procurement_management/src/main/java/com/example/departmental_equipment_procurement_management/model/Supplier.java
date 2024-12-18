package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierID")
    private Integer supplierID;

    @Column(name = "supplierName", nullable = false)
    private String supplierName;

    @Column(name = "contactInfo")
    private String contactInfo;

    @Column(name = "address")
    private String address;
}
