package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

@Entity
@AttributeOverride(
        name = "contact",
        column = @Column(name = "contact", insertable = false, updatable = false)
)
public class PatientContact extends PersonContact {
    @EmbeddedId
    private PatientContactId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("patientId")
    @JoinColumn(name = "P_Id")
    private Patient patient;
    
    
    // Constructors
    
    public PatientContact() {
    }
    
    public PatientContact(String contact, Patient patient) {
        super(contact);
        this.patient = patient;
        this.id = new PatientContactId(patient.getId(), contact);
    }
    
    // Getters and Setters
    
    public PatientContactId getId() {
        return id;
    }
    
    public void setId(PatientContactId id) {
        this.id = id;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
}
