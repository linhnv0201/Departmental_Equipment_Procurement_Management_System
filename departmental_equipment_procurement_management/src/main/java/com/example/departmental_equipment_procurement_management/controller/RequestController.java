package com.example.departmental_equipment_procurement_management.controller;

import com.example.departmental_equipment_procurement_management.dto.RequestDTO;
import com.example.departmental_equipment_procurement_management.model.*;
import com.example.departmental_equipment_procurement_management.repository.DepartmentRepository;
import com.example.departmental_equipment_procurement_management.service.EmployeeService;
import com.example.departmental_equipment_procurement_management.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentRepository departmentRepository;

    // Lấy tất cả yêu cầu
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    // Lấy yêu cầu theo ID request
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Request> getRequestById(@PathVariable Integer id) {
        Optional<Request> request = requestService.getRequestById(id);
        return request.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Lấy all request của 1 nhân viên
    @GetMapping("/all/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Request>> getAllRequestOf1Employee(@PathVariable Integer id) {
        List<Request> list = requestService.getAllRequestsOfEmployee(id);
        return ResponseEntity.ok(list);
    }

    // Lấy all request của 1 department
    @GetMapping("/all/department/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<Request>> getAllRequestOf1Department(@PathVariable Integer id) {
        List<Request> list = requestService.getAllRequestsOfDepartment(id);
        return ResponseEntity.ok(list);
    }

    // Lấy all request của người hiện tại
    @GetMapping("/all/currentuser")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<Request>> getAllRequestOfCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeService.findEmployeeByEmail(email);
        List<Request> list = requestService.getAllRequestsOfEmployee(employee.getEmployeeID());
        return ResponseEntity.ok(list);
    }


    // Lấy all request của phòng ban của người hiện tại
    @GetMapping("all/department/currentuser")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<Request>> getAllRequestOfDepartmentCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Department department = employeeService.findDepartmentByEmail(email);
        List<Request> list = requestService.getAllRequestsOfDepartment(department.getDepartmentID());
        return ResponseEntity.ok(list);
    }

    //Lấy equipment theo từng request
    @GetMapping("equipment/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<RequestEquipment>> getRequestEquipmentById(@PathVariable Integer id) {
        List<RequestEquipment> list = requestService.getEquipmentByRequestId(id);
        return ResponseEntity.ok(list);
    }

    // Tạo yêu cầu mới
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Request> createRequest(@RequestBody RequestDTO requestDTO) {
        try {
            Request savedRequest = requestService.saveRequest(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Cập nhật trạng thái yêu cầu
    @PutMapping("/changestatus/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Request> updateRequestStatus(@PathVariable Integer id, @RequestBody String status) {
        Request updatedRequest = requestService.updateRequestStatus(id, status);
        if (updatedRequest != null) {
            return ResponseEntity.ok(updatedRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Toàn bộ thiết bị đã mua
    @GetMapping("/purchasedEquipment")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PurchasedEquipment>> getPurchasedEquipment() {
        List<PurchasedEquipment> list = requestService.getAllPurchasedEquipments();
        return ResponseEntity.ok(list);
    }

    // Lấy thiết bị đã mua cúa 1 phòng ban
    @GetMapping("purchasedEquipment/department/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PurchasedEquipment>> getPurchasedEquipmentByDepartment(@PathVariable Integer id) {
        List<PurchasedEquipment> list = requestService.getPurchasedEquipmentsDepartment(id);
        return ResponseEntity.ok(list);
    }

    // Lấy thiết bị đã mua cúa phòng ban của người hiện tại
    @GetMapping("purchasedEquipment/department/currentuser")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<PurchasedEquipment>> getPurchasedEquipmentByCurrentUserDepartment() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Department department = employeeService.findDepartmentByEmail(email);
        List<PurchasedEquipment> list = requestService.getPurchasedEquipmentsDepartment(department.getDepartmentID());
        return ResponseEntity.ok(list);
    }

    // Lấy thiết bị đã mua cúa 1 request
    @GetMapping("purchasedEquipment/request/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<PurchasedEquipment>> getPurchasedEquipmentByRequest(@PathVariable Integer id) {
        List<PurchasedEquipment> list = requestService.getPurchasedEquipmentsByRequestId(id);
        return ResponseEntity.ok(list);
    }

}
