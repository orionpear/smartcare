package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import lk.ac.sltc.ccs1303.smartcare.entity.LabTest;
import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.LabTestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LabTestService {
    
    // Does not change for the rest of the application
    private static final String PENDING_STATUS = "Pending";
    private static final String COMPLETED_STATUS = "Completed";
    
    private final LabTestRepository labTestRepository;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    
    // Constructors
    
    public LabTestService(LabTestRepository labTestRepository,
                          AppointmentService appointmentService,
                          PatientService patientService) {
        this.labTestRepository = labTestRepository;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }
    
    // --- CRUD methods ---
    
    // Add
    public LabTest addTest(Long appointmentId, String testName, LocalDate date, String techName, Double testFee) {
        Appointment appointment = appointmentService.getById(appointmentId);
        
        LabTest labTest = new LabTest();
        labTest.setAppointment(appointment);
        labTest.setTestName(testName);
        labTest.setDate(date);
        labTest.setTechName(techName);
        labTest.setTestFee(testFee);
        labTest.setStatus(PENDING_STATUS);
        return labTestRepository.save(labTest);
    }
    
    // Update Result
    public LabTest updateResult(Long id, String testResult) {
        LabTest labTest = getById(id);
        labTest.setTestResult(testResult);
        labTest.setStatus(COMPLETED_STATUS);
        return labTestRepository.save(labTest);
    }
    
    // Update Status
    public LabTest updateStatus(Long id, String status) {
        LabTest labTest = getById(id);
        labTest.setStatus(status);
        return labTestRepository.save(labTest);
    }
    
    // --- Views ---
    
    // Get by Id
    public LabTest getById(Long id) {
        return labTestRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Lab Test Not Found: " + id));
    }
    
    // Get All
    public List<LabTest> getAllTests() {
        return labTestRepository.findAll();
    }
    
    // Get Tests for Appointment
    public List<LabTest> getTestsForAppointment(Long appointmentId) {
        Appointment appointment = appointmentService.getById(appointmentId);
        return labTestRepository.findByAppointment(appointment);
    }
    
    // Get History for Patient
    public List<LabTest> getHistoryForPatient(Long patientId) {
        Patient patient = patientService.getById(patientId);
        return labTestRepository.findByAppointment_Patient(patient);
    }
}