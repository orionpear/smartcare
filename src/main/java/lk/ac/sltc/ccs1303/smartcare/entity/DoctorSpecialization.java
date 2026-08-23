package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

@Entity
public class DoctorSpecialization {
    @EmbeddedId
    private DoctorSpecializationId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("doctorId")
    @JoinColumn(name = "Doc_Id")
    private Doctor doctor;
    
    
    // Constructors
    
    public DoctorSpecialization() {
    }
    
    public DoctorSpecialization(Doctor doctor, String specialization) {
        this.id = new DoctorSpecializationId(doctor.getId(), specialization);
        this.doctor = doctor;
    }
    
    // Getters and Setters
    
    
    public DoctorSpecializationId getId() {
        return id;
    }
    
    public void setId(DoctorSpecializationId id) {
        this.id = id;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
