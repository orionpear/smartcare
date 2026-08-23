package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.*;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.AdmissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdmissionService {
    
    // Does not change for the rest of the application
    private static final String ADMITTED_STATUS = "Admitted";
    private static final String DISCHARGED_STATUS = "Discharged";
    
    private final AdmissionRepository admissionRepository;
    private final RoomService roomService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    
    // Constructors
    
    public AdmissionService(AdmissionRepository admissionRepository,
                            RoomService roomService,
                            AppointmentService appointmentService,
                            PatientService patientService) {
        this.admissionRepository = admissionRepository;
        this.roomService = roomService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }
    
    // --- CRUD methods ---
    
    // Admit
    public Admission admitPatient(Long appointmentId, Long roomId, Long bedNum, LocalDate admissionDate) {
        Appointment appointment = appointmentService.getById(appointmentId);
        Bed bed = roomService.getBed(roomId, bedNum);
        
        if (admissionRepository.existsByBedAndDischargeDateIsNull(bed)) {
            throw new IllegalStateException("Bed is already occupied.");
        }
        
        Admission admission = new Admission();
        admission.setAppointment(appointment);
        admission.setBed(bed);
        admission.setAdmissionDate(admissionDate);
        admission.setStatus(ADMITTED_STATUS);
        
        return admissionRepository.save(admission);
    }
    
    // Allocate / Transfer Room
    public Admission allocateRoom(Long admissionId, Long newRoomId, Long newBedNum) {
        Admission admission = getById(admissionId);
        Bed newBed = roomService.getBed(newRoomId, newBedNum);
        
        if (admissionRepository.existsByBedAndDischargeDateIsNull(newBed)) {
            throw new IllegalStateException("Bed is already occupied.");
        }
        
        admission.setBed(newBed);
        return admissionRepository.save(admission);
    }
    
    // Discharge
    public Admission discharge(Long admissionId, LocalDate dischargeDate) {
        Admission admission = getById(admissionId);
        admission.setDischargeDate(dischargeDate);
        admission.setStatus(DISCHARGED_STATUS);
        return admissionRepository.save(admission);
    }
    
    // --- Views ---
    
    // Get by Id
    public Admission getById(Long id) {
        return admissionRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Admission Not Found: " + id));
    }
    
    // Get All
    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }
    
    // Get Admissions for Patient
    public List<Admission> getAdmissionsForPatient(Long patientId) {
        Patient patient = patientService.getById(patientId);
        return admissionRepository.findByAppointment_Patient(patient);
    }
    
    // Get Admissions for Appointment
    public List<Admission> getAdmissionsForAppointment(Long appointmentId) {
        Appointment appointment = appointmentService.getById(appointmentId);
        return admissionRepository.findByAppointment(appointment);
    }
}