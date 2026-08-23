package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.TreatmentRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.TreatmentUpdateRequest;
import lk.ac.sltc.ccs1303.smartcare.entity.Treatment;
import lk.ac.sltc.ccs1303.smartcare.service.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {
    
    private final TreatmentService treatmentService;
    
    // Constructors
    
    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }
    
    // --- CRUD methods ---
    
    // Add
    @PostMapping
    public ResponseEntity<Treatment> addTreatment(@RequestBody TreatmentRequest request) {
        Treatment treatment = treatmentService.addTreatment(
                request.appointmentId(), request.date(), request.diagnosis(),
                request.prescriptionDetails(), request.treatmentNotes(), request.treatFee());
        return ResponseEntity.status(HttpStatus.CREATED).body(treatment);
    }
    
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long id,
                                                     @RequestBody TreatmentUpdateRequest request) {
        return ResponseEntity.ok(treatmentService.updateTreatment(id, request.diagnosis(),
                request.prescriptionDetails(), request.treatmentNotes(), request.treatFee()));
    }
    
    // --- Views ---
    
    // Get All
    @GetMapping
    public ResponseEntity<List<Treatment>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }
    
    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) {
        return ResponseEntity.ok(treatmentService.getById(id));
    }
    
    // Get Treatments for Appointment
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<Treatment>> getTreatmentsForAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(treatmentService.getTreatmentsForAppointment(appointmentId));
    }
    
    // Get Medical History for Patient
    @GetMapping("/patient/{patientId}/history")
    public ResponseEntity<List<Treatment>> getMedicalHistoryForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(treatmentService.getMedicalHistoryForPatient(patientId));
    }
}