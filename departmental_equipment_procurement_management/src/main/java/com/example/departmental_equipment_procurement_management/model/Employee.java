package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    private String name;
    private String position;
    private String phoneNum;
    private String email;

    @ManyToOne
    @JoinColumn(name = "depId")
    private Department department;

}
