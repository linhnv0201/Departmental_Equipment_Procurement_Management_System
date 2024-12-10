package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "purchased_equipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchasedEquipmentID")
    private Integer purchasedEquipmentID;

    @ManyToOne
    @JoinColumn(name = "equipmentID")
    private Equipment equipment;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "purchaseDate")
    private Date purchaseDate;

    @Column(name = "purchasePrice")
    private Double purchasePrice;

    @ManyToOne
    @JoinColumn(name = "departmentID")
    private Department department;
}

