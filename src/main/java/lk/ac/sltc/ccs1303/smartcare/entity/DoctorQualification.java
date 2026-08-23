package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

@Entity
public class DoctorQualification {
    @EmbeddedId
    private DoctorQualificationId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("doctorId")
    @JoinColumn(name = "Doc_Id")
    private Doctor doctor;
    
    
    // Constructors
    
    public DoctorQualification() {
    }
    
    public DoctorQualification(Doctor doctor, String qualification) {
        this.id = new DoctorQualificationId(doctor.getId(), qualification);
        this.doctor = doctor;
    }
    
    // Getters and Setters
    
    public DoctorQualificationId getId() {
        return id;
    }
    
    public void setId(DoctorQualificationId id) {
        this.id = id;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
