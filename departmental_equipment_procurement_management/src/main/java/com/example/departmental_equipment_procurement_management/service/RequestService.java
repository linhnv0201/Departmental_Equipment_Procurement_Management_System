package com.example.departmental_equipment_procurement_management.service;

import com.example.departmental_equipment_procurement_management.model.Approval;
import com.example.departmental_equipment_procurement_management.model.Department;
import com.example.departmental_equipment_procurement_management.model.Request;
import com.example.departmental_equipment_procurement_management.model.RequestEquipment;
import com.example.departmental_equipment_procurement_management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    RequestEquipmentRepository requestEquipmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Request createRequest(Request request, List<RequestEquipment> equipmentList) {
        // Lưu yêu cầu vào DB
        request.setStatus("Pending");
        requestRepository.save(request);

        // Lưu các thiết bị yêu cầu
        for (RequestEquipment equipment : equipmentList) {
            equipment.setRequest(request);
            requestEquipmentRepository.save(equipment);
        }

        return request;
    }

//    public boolean approveRequest(int requestId, int approverId, String comments) {
//        Optional<Request> request = requestRepository.findById(requestId);
//        if (request.isPresent()) {
//            Request req = request.get();
//            if (req.getStatus().equals("Pending")) {
//                // Kiểm tra ngân sách phòng ban
//                Department department = departmentRepository.findById(req.getDepartmentID()).get();
//                if (department.getBudget() < calculateTotalCost(req)) {
//                    return false; // Ngân sách không đủ
//                }
//
//                // Phê duyệt yêu cầu
//                Approval approval = new Approval();
//                approval.setRequest(req);
//                approval.setApprovedBy(approverId);
//                approval.setComments(comments);
//                approval.setApprovedDate(LocalDate.now());
//                approvalRepository.save(approval);
//
//                req.setStatus("Approved");
//                requestRepository.save(req);
//                return true;
//            }
//        }
//        return false;
//    }

//    private BigDecimal calculateTotalCost(Request request) {
//        BigDecimal totalCost = BigDecimal.ZERO;
//        for (RequestEquipment equipment : request.getRequestEquipments()) {
//            totalCost = totalCost.add(equipment.getEquipment().getEstimatedCost().multiply(new BigDecimal(equipment.getQuantity())));
//        }
//        return totalCost;
//    }
}

