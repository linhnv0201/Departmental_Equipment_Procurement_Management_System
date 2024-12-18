package com.example.departmental_equipment_procurement_management.controller;

import com.example.departmental_equipment_procurement_management.dto.RequestDTO;
import com.example.departmental_equipment_procurement_management.model.PurchasedEquipment;
import com.example.departmental_equipment_procurement_management.model.Request;
import com.example.departmental_equipment_procurement_management.model.RequestEquipment;
import com.example.departmental_equipment_procurement_management.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    // Lấy tất cả yêu cầu
    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    // Lấy yêu cầu theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Integer id) {
        Optional<Request> request = requestService.getRequestById(id);
        return request.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Lấy equipment theo từng request
    @GetMapping("equipment/{id}")
    public ResponseEntity<List<RequestEquipment>> getRequestEquipmentById(@PathVariable Integer id) {
        List<RequestEquipment> list = requestService.getEquipmentByRequestId(id);
        return ResponseEntity.ok(list);
    }

    // Tạo yêu cầu mới
    @PostMapping
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
    public ResponseEntity<Request> updateRequestStatus(@PathVariable Integer id, @RequestBody String status) {
        Request updatedRequest = requestService.updateRequestStatus(id, status);
        if (updatedRequest != null) {
            return ResponseEntity.ok(updatedRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    // Xóa yêu cầu
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id) {
//        requestService.deleteRequest(id);
//        return ResponseEntity.noContent().build();
//    }
    @GetMapping("/purchasedEquipment")
    public ResponseEntity<List<PurchasedEquipment>> getPurchasedEquipment() {
        List<PurchasedEquipment> list = requestService.getAllPurchasedEquipments();
        return ResponseEntity.ok(list);
    }

    @GetMapping("purchasedEquipment/department/{id}")
    public ResponseEntity<List<PurchasedEquipment>> getPurchasedEquipmentByDepartment(@PathVariable Integer id) {
        List<PurchasedEquipment> list = requestService.getPurchasedEquipmentsDepartment(id);
        return ResponseEntity.ok(list);
    }
}
