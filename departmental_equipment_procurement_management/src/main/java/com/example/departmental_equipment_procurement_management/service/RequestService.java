package com.example.departmental_equipment_procurement_management.service;

import com.example.departmental_equipment_procurement_management.dto.RequestDTO;
import com.example.departmental_equipment_procurement_management.model.*;
import com.example.departmental_equipment_procurement_management.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestEquipmentRepository requestEquipmentRepository;

    @Autowired
    private PurchasedEquipmentRepository purchasedEquipmentRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    // Lấy tất cả yêu cầu
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    // Lấy yêu cầu theo ID
    public Optional<Request> getRequestById(Integer requestId) {
        return requestRepository.findById(requestId);
    }

    // Lấy tất cả thiết bị đã mua
    public List<PurchasedEquipment> getAllPurchasedEquipments() {
        return purchasedEquipmentRepository.findAll();
    }

    // Lấy thiết bị đã mua từng phòng ban
    public List<PurchasedEquipment> getPurchasedEquipmentsDepartment(Integer departmentId) {
        return purchasedEquipmentRepository.findByDepartmentId(departmentId);
    }
    // Lấy thiết bị đã mua theo request id
    public List<PurchasedEquipment> getPurchasedEquipmentsByRequestId(Integer requestId) {
        return purchasedEquipmentRepository.findByRequestID(requestId);
    }


    // Lưu yêu cầu mới từ RequestDTO
    public Request saveRequest(RequestDTO requestDTO) throws Exception {
        // Chuyển đổi từ RequestDTO sang thực thể Request
        Request request = new Request();

        // Tìm Employee và Department theo tên
        Employee employee = employeeRepository.findById(requestDTO.getEmployeeID())
                .orElseThrow(() -> new Exception("Employee not found"));
        request.setEmployee(employee);

        request.setDepartment(employee.getDepartment());

        request.setDescription(requestDTO.getDescription());

        request.setStatus("Pending");

        request.setRequestDate(new Date()); // Lấy thời gian hiện tại

        // Lưu thông tin Request trước
        Request savedRequest = requestRepository.save(request);

        // Duyệt qua danh sách thiết bị và lưu vào bảng request_equipments
        List<RequestEquipment> requestEquipments = requestDTO.getRequestEquipments().stream()
                .map(dto -> {
                    RequestEquipment requestEquipment = new RequestEquipment();
                    requestEquipment.setRequest(savedRequest);

                    // Lấy thiết bị từ EquipmentRepository
                    Optional<Equipment> equipment = equipmentRepository.findById(dto.getEquipmentID());
                    if (equipment.isPresent()) {
                        requestEquipment.setEquipment(equipment.get());
                        requestEquipment.setQuantity(dto.getQuantity());
                    }
                    return requestEquipment;
                })
                .collect(Collectors.toList());

        // Lưu danh sách requestEquipments vào DB
        requestEquipmentRepository.saveAll(requestEquipments);

        return savedRequest;  // Trả về Request đã lưu
    }

    public List<RequestEquipment> getEquipmentByRequestId(Integer requestId) {
        return requestEquipmentRepository.findByRequestId(requestId);
    }


//     Cập nhật trạng thái của yêu cầu
    @Transactional
    public Request updateRequestStatus(Integer requestId, String status) {
        Optional<Request> requestOpt = requestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            Request request = requestOpt.get();
            request.setStatus(status);

            double totalCost = 0;
            if ("APPROVED".equalsIgnoreCase(request.getStatus())) {
                // Khi trạng thái là "APPROVED", chuyển các RequestEquipment thành PurchasedEquipment
                for (RequestEquipment requestEquipment : requestEquipmentRepository.findByRequestId(request.getRequestID())) {
                    PurchasedEquipment purchasedEquipment = new PurchasedEquipment();

                    purchasedEquipment.setEquipment(requestEquipment.getEquipment());
                    purchasedEquipment.setRequest(requestEquipment.getRequest());
                    purchasedEquipment.setQuantity(requestEquipment.getQuantity());
                    purchasedEquipment.setPurchaseDate(new Date());
                    purchasedEquipment.setPurchasePrice(requestEquipment.getEquipment().getCurrentPrice());
                    purchasedEquipment.setDepartment(request.getDepartment());

                    totalCost += requestEquipment.getQuantity() * requestEquipment.getEquipment().getCurrentPrice();
                    // Lưu PurchasedEquipment vào DB
                    purchasedEquipmentRepository.save(purchasedEquipment);
                }
            }

            double currentBudget = request.getDepartment().getBudget();
            if (currentBudget >= totalCost) {
                // Nếu ngân sách đủ, trừ đi chi phí
                request.getDepartment().setBudget(currentBudget - totalCost);
            } else {
                // Nếu ngân sách không đủ, trả về null hoặc throw exception
                throw new IllegalArgumentException("Ngân sách không đủ để duyệt yêu cầu.");
            }

            return requestRepository.save(request); // Cập nhật trạng thái
        } else {
            return null; // Nếu không tìm thấy Request
        }
    }

    //tìm request theo employee tao ra
    public List<Request> getAllRequestsOfEmployee(Integer employeeID) {
        return requestRepository.findAllRequestOfAnEmployee(employeeID);
    }

    // Lấy request của 1 department theo departmentId
    public List<Request> getAllRequestsOfDepartment(Integer departmentID) {
        return requestRepository.findAllRequestOfAnDepartment(departmentID);
    }

}
