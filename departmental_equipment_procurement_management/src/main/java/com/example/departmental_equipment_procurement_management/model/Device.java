package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer devId;

    @ManyToOne
    @JoinColumn(name = "devInfoId")
    private DeviceInfo deviceInfo;

    @ManyToOne
    @JoinColumn(name = "depId")
    private Department department;

    private Integer price;

}

