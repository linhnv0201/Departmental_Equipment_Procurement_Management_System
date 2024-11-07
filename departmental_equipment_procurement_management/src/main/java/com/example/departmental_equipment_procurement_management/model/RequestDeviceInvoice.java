package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request_device_invoice")
public class RequestDeviceInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "reqId")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "devId")
    private Device device;

}
