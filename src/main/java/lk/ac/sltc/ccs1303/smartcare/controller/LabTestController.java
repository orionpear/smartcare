package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.LabTestRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.LabTestResultRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.LabTestStatusRequest;
import lk.ac.sltc.ccs1303.smartcare.entity.LabTest;
import lk.ac.sltc.ccs1303.smartcare.service.LabTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab-tests")
public class LabTestController {
    
    private final LabTestService labTestService;
    
    // Constructors
    
    public LabTestController(LabTestService labTestService) {
        this.labTestService = labTestService;
    }
    
    // --- CRUD methods ---
    
    // Add
    @PostMapping
    public ResponseEntity<LabTest> addTest(@RequestBody LabTestRequest request) {
        LabTest labTest = labTestService.addTest(
                request.appointmentId(), request.testName(), request.date(), request.techName(), request.testFee());
        return ResponseEntity.status(HttpStatus.CREATED).body(labTest);
    }
    
    // Update Result
    @PatchMapping("/{id}/result")
    public ResponseEntity<LabTest> updateResult(@PathVariable Long id, @RequestBody LabTestResultRequest request) {
        return ResponseEntity.ok(labTestService.updateResult(id, request.testResult()));
    }
    
    // Update Status
    @PatchMapping("/{id}/status")
    public ResponseEntity<LabTest> updateStatus(@PathVariable Long id, @RequestBody LabTestStatusRequest request) {
        return ResponseEntity.ok(labTestService.updateStatus(id, request.status()));
    }
    
    // --- Views ---
    
    // Get All
    @GetMapping
    public ResponseEntity<List<LabTest>> getAllTests() {
        return ResponseEntity.ok(labTestService.getAllTests());
    }
    
    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<LabTest> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok(labTestService.getById(id));
    }
    
    // Get Tests for Appointment
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<LabTest>> getTestsForAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(labTestService.getTestsForAppointment(appointmentId));
    }
    
    // Get History for Patient
    @GetMapping("/patient/{patientId}/history")
    public ResponseEntity<List<LabTest>> getHistoryForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(labTestService.getHistoryForPatient(patientId));
    }
}