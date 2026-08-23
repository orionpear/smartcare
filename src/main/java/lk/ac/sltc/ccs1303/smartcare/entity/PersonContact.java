package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class PersonContact {
    protected String contact;
    
    // Constructors
    public PersonContact() {
    }
    
    public PersonContact(String contact) {
        this.contact = contact;
    }
    
    // Getters and Setters
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
}
