package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DoctorContactId implements Serializable {
    
    @Column(name = "Doc_Id")
    private Long doctorId;
    
    private String contact;
    
    // Constructors
    
    public DoctorContactId() {
    }
    
    public DoctorContactId(Long doctorId, String contact) {
        this.doctorId = doctorId;
        this.contact = contact;
    }
    
    // Getters and Setters
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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
        if (!(o instanceof DoctorContactId that)) return false;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(contact, that.contact);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(doctorId, contact);
    }
}