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

    // Lấy yêu cầu theo ID request
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Integer id) {
        Optional<Request> request = requestService.getRequestById(id);
        return request.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Lấy all request của 1 nhân viên
    @GetMapping("/all/{id}")
    public ResponseEntity<List<Request>> getAllRequestOf1Employee(@PathVariable Integer id) {
        List<Request> list = requestService.getAllRequestsOfEmployee(id);
        return ResponseEntity.ok(list);
    }


    // Lấy all request của 1 phòng ban bằng id phòng
    @GetMapping("all/department/{id}")
    public ResponseEntity<List<Request>> getAllRequestOf1Deparment(@PathVariable Integer id) {
        List<Request> list = requestService.getAllRequestsOfDepartment(id);
        return ResponseEntity.ok(list);
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

    // Toàn bộ thiết bị đã mua
    @GetMapping("/purchasedEquipment")
    public ResponseEntity<List<PurchasedEquipment>> getPurchasedEquipment() {
        List<PurchasedEquipment> list = requestService.getAllPurchasedEquipments();
        return ResponseEntity.ok(list);
    }

    // Lấy thiết bị đã mua cúa 1 phòng ban
    @GetMapping("purchasedEquipment/department/{id}")
    public ResponseEntity<List<PurchasedEquipment>> getPurchasedEquipmentByDepartment(@PathVariable Integer id) {
        List<PurchasedEquipment> list = requestService.getPurchasedEquipmentsDepartment(id);
        return ResponseEntity.ok(list);
    }


}
