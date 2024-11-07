package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reqId;

    private LocalDateTime date;
    private String description;
    private String reason;
    private String state;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;
}

