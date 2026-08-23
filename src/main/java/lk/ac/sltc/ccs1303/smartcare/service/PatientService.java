package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import lk.ac.sltc.ccs1303.smartcare.entity.PatientContact;
import lk.ac.sltc.ccs1303.smartcare.entity.PatientContactId;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.PatientContactRepository;
import lk.ac.sltc.ccs1303.smartcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {
    
    private final PatientRepository patientRepository;
    private final PatientContactRepository patientContactRepository;
    
    // Constructors
    
    public PatientService(PatientRepository patientRepository, PatientContactRepository patientContactRepository) {
        this.patientRepository = patientRepository;
        this.patientContactRepository = patientContactRepository;
    }
    
    // --- CRUD methods ---
    
    // Add
    public Patient addPatient(String firstName, String lastName, String address, String blood, LocalDate dob) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAddress(address);
        patient.setBlood(blood);
        patient.setDoB(dob);
        return patientRepository.save(patient);
    }
    
    // Update
    public Patient updatePatient(Long id, String firstName, String lastName, String address, String blood, LocalDate dob) {
        Patient patient = getById(id);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAddress(address);
        patient.setBlood(blood);
        patient.setDoB(dob);
        return patientRepository.save(patient);
    }
    
    // Delete
    public void deletePatient(Long id) {
        Patient patient = getById(id);
        patientRepository.delete(patient);
    }
    
    // Search
    public List<Patient> searchByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Get by Id
    public Patient getById(Long id) {
        return patientRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Patient Not Found: " + id));
    }
    
    // Get All
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
    // --- Contacts ---
    
    // Add
    public PatientContact addContact(Long patientId, String contact) {
        Patient patient = getById(patientId);
        PatientContact patientContact = new PatientContact(contact, patient);
        patient.getContacts().add(patientContact);
        return patientContactRepository.save(patientContact);
    }
    
    // Remove
    public void removeContact(Long patientId, String contact) {
        PatientContactId id = new PatientContactId(patientId, contact);
        PatientContact patientContact = patientContactRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException("Error: Patient Contact Not Found: " + id));
        patientContactRepository.delete(patientContact);
    }
    
    
    public List<PatientContact> getContacts(Long patientId) {
        Patient patient = getById(patientId);
        return patient.getContacts();
    }
    
    
    
}
