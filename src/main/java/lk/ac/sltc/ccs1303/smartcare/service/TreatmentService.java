package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import lk.ac.sltc.ccs1303.smartcare.entity.Treatment;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TreatmentService {
    
    private final TreatmentRepository treatmentRepository;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    
    // Constructors
    
    public TreatmentService(TreatmentRepository treatmentRepository,
                            AppointmentService appointmentService,
                            PatientService patientService) {
        this.treatmentRepository = treatmentRepository;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }
    
    // --- CRUD methods ---
    
    // Record Diagnosis / Treatment
    public Treatment addTreatment(Long appointmentId, LocalDate date, String diagnosis,
                                  String prescriptionDetails, String treatmentNotes, Double treatFee) {
        Appointment appointment = appointmentService.getById(appointmentId);
        
        Treatment treatment = new Treatment();
        treatment.setAppointment(appointment);
        treatment.setDate(date);
        treatment.setDiagnosis(diagnosis);
        treatment.setPrescriptionDetails(prescriptionDetails);
        treatment.setTreatmentNotes(treatmentNotes);
        treatment.setTreatFee(treatFee);
        
        return treatmentRepository.save(treatment);
    }
    
    // Update
    public Treatment updateTreatment(Long id, String diagnosis, String prescriptionDetails,
                                     String treatmentNotes, Double treatFee) {
        Treatment treatment = getById(id);
        treatment.setDiagnosis(diagnosis);
        treatment.setPrescriptionDetails(prescriptionDetails);
        treatment.setTreatmentNotes(treatmentNotes);
        treatment.setTreatFee(treatFee);
        return treatmentRepository.save(treatment);
    }
    
    // --- Views ---
    
    // Get by Id
    public Treatment getById(Long id) {
        return treatmentRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Treatment Not Found: " + id));
    }
    
    // Get All
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }
    
    // Get Treatments for Appointment
    public List<Treatment> getTreatmentsForAppointment(Long appointmentId) {
        Appointment appointment = appointmentService.getById(appointmentId);
        return treatmentRepository.findByAppointment(appointment);
    }
    
    // Get Medical History for Patient
    public List<Treatment> getMedicalHistoryForPatient(Long patientId) {
        Patient patient = patientService.getById(patientId);
        return treatmentRepository.findByAppointment_PatientOrderByDateDesc(patient);
    }
}