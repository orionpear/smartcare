package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DoctorQualificationId implements Serializable {
    
    @Column(name = "Doc_Id")
    private Long doctorId;
    
    private String qualification;
    
    // Constructors
    
    public DoctorQualificationId() {
    }
    
    public DoctorQualificationId(Long doctorId, String qualification) {
        this.doctorId = doctorId;
        this.qualification = qualification;
    }
    
    // Getters and Setters
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public String getQualification() {
        return qualification;
    }
    
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    
    // equals() and hashCode()
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoctorQualificationId that)) return false;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(qualification, that.qualification);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(doctorId, qualification);
    }
}