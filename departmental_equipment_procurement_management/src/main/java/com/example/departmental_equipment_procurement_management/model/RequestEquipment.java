package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "request_equipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestEquipmentID")
    private Integer requestEquipmentID;

    @ManyToOne
    @JoinColumn(name = "requestID")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "equipmentID")
    private Equipment equipment;

    @Column(name = "quantity")
    private Integer quantity;
}

