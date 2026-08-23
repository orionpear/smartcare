package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.ContactRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.PatientRequest;
import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import lk.ac.sltc.ccs1303.smartcare.entity.PatientContact;
import lk.ac.sltc.ccs1303.smartcare.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    
    private final PatientService patientService;
    
    // Constructors
    
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    
    // --- CRUD methods ---
    
    // Add
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody PatientRequest request) {
        Patient patient = patientService.addPatient(request.firstName(), request.lastName(),
                request.address(), request.blood(), request.dob());
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }
    
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.updatePatient(id, request.firstName(), request.lastName(),
                request.address(), request.blood(), request.dob()));
    }
    
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    
    // Get All
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
    
    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getById(id));
    }
    
    // Search
    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(patientService.searchByName(name));
    }
    
    // --- Contacts ---
    
    // Add
    @PostMapping("/{id}/contacts")
    public ResponseEntity<PatientContact> addContact(@PathVariable Long id, @RequestBody ContactRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addContact(id, request.contact()));
    }
    
    // Remove
    @DeleteMapping("/{id}/contacts/{contact}")
    public ResponseEntity<Void> removeContact(@PathVariable Long id, @PathVariable String contact) {
        patientService.removeContact(id, contact);
        return ResponseEntity.noContent().build();
    }
    
    // Get
    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<PatientContact>> getContacts(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getContacts(id));
    }
}