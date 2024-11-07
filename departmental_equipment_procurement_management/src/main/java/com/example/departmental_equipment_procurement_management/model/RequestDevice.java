package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request_device")
public class RequestDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reqId;

    @ManyToOne
    @JoinColumn(name = "devInfoId")
    private DeviceInfo deviceInfo;

    private Integer quantity;

}

