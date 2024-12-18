package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipmentID")
    private Integer equipmentID;

    @Column(name = "equipmentName", nullable = false)
    private String equipmentName;

    @Column(name = "equipmentDescription")
    private String equipmentDescription;

    @Column(name = "purchasePeriod")
    private Integer purchasePeriod;

    @ManyToOne
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    @Column(name = "currentPrice")
    private Double currentPrice;
}

