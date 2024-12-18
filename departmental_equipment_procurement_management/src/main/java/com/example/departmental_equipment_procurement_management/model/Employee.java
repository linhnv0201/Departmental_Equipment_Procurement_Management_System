package com.example.departmental_equipment_procurement_management.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeID")
    private Integer employeeID;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "position", nullable = false)
    private String position;

    @ManyToOne
    @JoinColumn(name = "departmentID", referencedColumnName = "departmentID")
    private Department department;

    public String getRole() {
        // Kiểm tra xem phòng ban có tồn tại không và kiểm tra tên phòng ban
        if (this.department != null && "Phòng Quản lý".equals(this.department.getDepartmentName())) {
            return "ROLE_ADMIN";  // Vai trò admin cho phòng Quản lý
        }
        return "ROLE_USER";  // Vai trò user cho các phòng ban khác
    }

}

