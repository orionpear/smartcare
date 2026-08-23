package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DoctorSpecializationId implements Serializable {
    
    @Column(name = "Doc_Id")
    private Long doctorId;
    
    private String specialization;
    
    // Constructors
    
    public DoctorSpecializationId() {
    }
    
    public DoctorSpecializationId(Long doctorId, String specialization) {
        this.doctorId = doctorId;
        this.specialization = specialization;
    }
    
    // Getters and Setters
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    // equals() and hashCode()
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoctorSpecializationId that)) return false;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(specialization, that.specialization);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(doctorId, specialization);
    }
}