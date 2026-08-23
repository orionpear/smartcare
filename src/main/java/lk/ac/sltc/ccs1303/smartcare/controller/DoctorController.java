package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.*;
import lk.ac.sltc.ccs1303.smartcare.entity.Doctor;
import lk.ac.sltc.ccs1303.smartcare.entity.DoctorContact;
import lk.ac.sltc.ccs1303.smartcare.entity.DoctorQualification;
import lk.ac.sltc.ccs1303.smartcare.entity.DoctorSpecialization;
import lk.ac.sltc.ccs1303.smartcare.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    
    private final DoctorService doctorService;
    
    // Constructors
    
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    
    // --- CRUD methods ---
    
    // Add
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorRequest request) {
        Doctor doctor = doctorService.addDoctor(request.name(), request.consultFee(), request.departmentId());
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }
    
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody DoctorUpdateRequest request) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, request.name(), request.consultFee()));
    }
    
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
    
    // Get All
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
    
    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }
    
    // Search
    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(doctorService.searchByName(name));
    }
    
    // Assign to Department
    @PutMapping("/{id}/department")
    public ResponseEntity<Doctor> assignToDepartment(@PathVariable Long id,
                                                     @RequestBody AssignDepartmentRequest request) {
        return ResponseEntity.ok(doctorService.assignToDepartment(id, request.departmentId()));
    }
    
    // --- Contacts ---
    
    // Add
    @PostMapping("/{id}/contacts")
    public ResponseEntity<DoctorContact> addContact(@PathVariable Long id, @RequestBody ContactRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addContact(id, request.contact()));
    }
    
    // Remove
    @DeleteMapping("/{id}/contacts/{contact}")
    public ResponseEntity<Void> removeContact(@PathVariable Long id, @PathVariable String contact) {
        doctorService.removeContact(id, contact);
        return ResponseEntity.noContent().build();
    }
    
    // Get
    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<DoctorContact>> getContacts(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getContacts(id));
    }
    
    // --- Qualifications ---
    
    // Add
    @PostMapping("/{id}/qualifications")
    public ResponseEntity<DoctorQualification> addQualification(@PathVariable Long id,
                                                                @RequestBody QualificationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                       .body(doctorService.addQualification(id, request.qualification()));
    }
    
    // Remove
    @DeleteMapping("/{id}/qualifications/{qualification}")
    public ResponseEntity<Void> removeQualification(@PathVariable Long id, @PathVariable String qualification) {
        doctorService.removeQualification(id, qualification);
        return ResponseEntity.noContent().build();
    }
    
    // Get
    @GetMapping("/{id}/qualifications")
    public ResponseEntity<List<DoctorQualification>> getQualifications(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getQualifications(id));
    }
    
    // --- Specializations ---
    
    // Add
    @PostMapping("/{id}/specializations")
    public ResponseEntity<DoctorSpecialization> addSpecialization(@PathVariable Long id,
                                                                  @RequestBody SpecializationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                       .body(doctorService.addSpecialization(id, request.specialization()));
    }
    
    // Remove
    @DeleteMapping("/{id}/specializations/{specialization}")
    public ResponseEntity<Void> removeSpecialization(@PathVariable Long id, @PathVariable String specialization) {
        doctorService.removeSpecialization(id, specialization);
        return ResponseEntity.noContent().build();
    }
    
    // Get
    @GetMapping("/{id}/specializations")
    public ResponseEntity<List<DoctorSpecialization>> getSpecializations(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getSpecializations(id));
    }
}