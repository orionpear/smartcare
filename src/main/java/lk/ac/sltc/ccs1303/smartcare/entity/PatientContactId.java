package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PatientContactId implements Serializable {
    
    @Column(name = "P_Id")
    private Long patientId;
    
    private String contact;
    
    // Constructors
    
    public PatientContactId() {
    }
    
    public PatientContactId(Long patientId, String contact) {
        this.patientId = patientId;
        this.contact = contact;
    }
    
    // Getters and Setters
    
    public Long getPatientId() {
        return patientId;
    }
    
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    // equals() and hashCode()
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientContactId that)) return false;
        return Objects.equals(patientId, that.patientId) && Objects.equals(contact, that.contact);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(patientId, contact);
    }
}