package com.example.departmental_equipment_procurement_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "approvals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approvalID")
    private Integer approvalID;

    @ManyToOne
    @JoinColumn(name = "requestID")
    private Request request;

    @Column(name = "approvedDate")
    private Date approvedDate;

    @ManyToOne
    @JoinColumn(name = "approvedBy")
    private Employee approvedBy;

    @Column(name = "comments")
    private String comments;
}
