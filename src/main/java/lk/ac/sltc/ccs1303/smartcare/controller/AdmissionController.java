package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.AdmissionRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.DischargeRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.RoomAllocationRequest;
import lk.ac.sltc.ccs1303.smartcare.entity.Admission;
import lk.ac.sltc.ccs1303.smartcare.service.AdmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {
    
    private final AdmissionService admissionService;
    
    // Constructors
    
    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }
    
    // --- CRUD methods ---
    
    // Admit
    @PostMapping
    public ResponseEntity<Admission> admitPatient(@RequestBody AdmissionRequest request) {
        Admission admission = admissionService.admitPatient(
                request.appointmentId(), request.roomId(), request.bedNum(), request.admissionDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(admission);
    }
    
    // Allocate / Transfer Room
    @PutMapping("/{id}/room")
    public ResponseEntity<Admission> allocateRoom(@PathVariable Long id,
                                                  @RequestBody RoomAllocationRequest request) {
        return ResponseEntity.ok(admissionService.allocateRoom(id, request.roomId(), request.bedNum()));
    }
    
    // Discharge
    @PatchMapping("/{id}/discharge")
    public ResponseEntity<Admission> discharge(@PathVariable Long id, @RequestBody DischargeRequest request) {
        return ResponseEntity.ok(admissionService.discharge(id, request.dischargeDate()));
    }
    
    // --- Views ---
    
    // Get All
    @GetMapping
    public ResponseEntity<List<Admission>> getAllAdmissions() {
        return ResponseEntity.ok(admissionService.getAllAdmissions());
    }
    
    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(admissionService.getById(id));
    }
    
    // Get Admissions for Patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Admission>> getAdmissionsForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(admissionService.getAdmissionsForPatient(patientId));
    }
    
    // Get Admissions for Appointment
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<Admission>> getAdmissionsForAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(admissionService.getAdmissionsForAppointment(appointmentId));
    }
}