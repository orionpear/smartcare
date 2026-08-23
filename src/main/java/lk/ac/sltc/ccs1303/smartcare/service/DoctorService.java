package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.*;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.*;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorContactRepository doctorContactRepository;
    private final DoctorQualificationRepository doctorQualificationRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    private final DepartmentService departmentService;
    
    // Constructors
    
    public DoctorService(DoctorRepository doctorRepository, DoctorContactRepository doctorContactRepository, DoctorQualificationRepository doctorQualificationRepository, DoctorSpecializationRepository doctorSpecializationRepository, DepartmentService departmentService) {
        this.doctorRepository = doctorRepository;
        this.doctorContactRepository = doctorContactRepository;
        this.doctorQualificationRepository = doctorQualificationRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
        this.departmentService = departmentService;
    }
    
    // --- CRUD methods ---
    
    // Add
    public Doctor addDoctor(String name, Double consultFee, Long departmentId) {
        Department department = departmentService.getById(departmentId);
        
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setConsultFee(consultFee);
        doctor.setDepartment(department);
        
        return doctorRepository.save(doctor);
    }
    
    // Update
    public Doctor updateDoctor(Long id, String name, Double consultFee) {
        Doctor doctor = getById(id);
        doctor.setName(name);
        doctor.setConsultFee(consultFee);
        return doctorRepository.save(doctor);
    }
    
    // Delete
    public void deleteDoctor(Long id) {
        Doctor doctor = getById(id);
        doctorRepository.delete(doctor);
    }
    
    // Search
    public List<Doctor> searchByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Assign to Department
    public Doctor assignToDepartment(Long doctorId, Long departmentId) {
        Doctor doctor = getById(doctorId);
        Department department = departmentService.getById(departmentId);
        doctor.setDepartment(department);
        return doctorRepository.save(doctor);
    }
    
    // Get by Id
    public Doctor getById(Long id) {
        return doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Error: Doctor Not Found: " + id));
    }
    
    // Get All
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    // --- Contacts ---
    
    // Add
    public DoctorContact addContact(Long doctorId, String contact) {
        Doctor doctor = getById(doctorId);
        DoctorContact doctorContact = new DoctorContact(contact, doctor);
        doctor.getContacts().add(doctorContact);
        return doctorContactRepository.save(doctorContact);
    }
    
    // Remove
    public void removeContact(Long doctorId, String contact) {
        DoctorContactId id = new DoctorContactId(doctorId, contact);
        DoctorContact doctorContact = doctorContactRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Error: Doctor Contact Not Found: " + id));
        doctorContactRepository.delete(doctorContact);
    }
    
    
    // Get
    public List<DoctorContact> getContacts(Long doctorId) {
        Doctor doctor = getById(doctorId);
        return doctor.getContacts();
    }
    
    // --- Qualifications ---
    
    // Add
    public DoctorQualification addQualification(Long doctorId, String qualification) {
        Doctor doctor = getById(doctorId);
        DoctorQualification doctorQualification = new DoctorQualification(doctor, qualification);
        doctor.getQualifications().add(doctorQualification);
        return doctorQualificationRepository.save(doctorQualification);
    }
    
    // Remove
    public void removeQualification(Long doctorId, String qualification) {
        DoctorQualificationId id = new DoctorQualificationId(doctorId, qualification);
        DoctorQualification doctorQualification = doctorQualificationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Error: Doctor Qualification Not Found: " + id));
        doctorQualificationRepository.delete(doctorQualification);
    }
    
    // Get
    public List<DoctorQualification> getQualifications(Long doctorId) {
        Doctor doctor = getById(doctorId);
        return doctor.getQualifications();
    }
    
    // --- Specializations ---
    
    // Add
    public DoctorSpecialization addSpecialization(Long doctorId, String specialization) {
        Doctor doctor = getById(doctorId);
        DoctorSpecialization doctorSpecialization = new DoctorSpecialization(doctor, specialization);
        doctor.getSpecializations().add(doctorSpecialization);
        return doctorSpecializationRepository.save(doctorSpecialization);
    }
    
    // Remove
    public void removeSpecialization(Long doctorId, String specialization) {
        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specialization);
        DoctorSpecialization doctorSpecialization = doctorSpecializationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Error: Doctor Specialization Not Found: " + id));
        doctorSpecializationRepository.delete(doctorSpecialization);
    }
    
    // Get
    public List<DoctorSpecialization> getSpecializations(Long doctorId) {
        Doctor doctor = getById(doctorId);
        return doctor.getSpecializations();
    }
    
    
    
    
    
}
