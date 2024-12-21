package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentID")
    private Integer departmentID;

    @Column(name = "departmentName", nullable = false)
    private String departmentName;

    @Column(name = "budget")
    private Double budget;
}
