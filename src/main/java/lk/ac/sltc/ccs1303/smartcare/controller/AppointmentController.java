package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.AppointmentRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.AppointmentUpdateRequest;
import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import lk.ac.sltc.ccs1303.smartcare.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    
    private final AppointmentService appointmentService;
    
    // Constructors
    
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    
    // --- CRUD methods ---
    
    // Book
    @PostMapping
    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentService.bookAppointment(
                request.doctorId(), request.patientId(), request.date(), request.time(), request.consultRoom());
        return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
    }
    
    // Update / Reschedule
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
                                                         @RequestBody AppointmentUpdateRequest request) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, request.date(), request.time(),
                request.consultRoom()));
    }
    
    // Cancel
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(id));
    }
    
    // --- Views ---
    
    // Get All
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
    
    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }
    
    // Get Schedule for Doctor
    @GetMapping("/doctor/{doctorId}/schedule")
    public ResponseEntity<List<Appointment>> getScheduleForDoctor(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.getScheduleForDoctor(doctorId, date));
    }
    
    // Get Appointments for Patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForPatient(patientId));
    }
    
    // Get Appointments for Doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsForDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForDoctor(doctorId));
    }
}