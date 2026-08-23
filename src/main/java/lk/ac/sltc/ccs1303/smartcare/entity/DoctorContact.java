package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

@Entity
@AttributeOverride(
        name = "contact",
        column = @Column(name = "contact", insertable = false, updatable = false)
)
public class DoctorContact extends PersonContact {
    @EmbeddedId
    private DoctorContactId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("doctorId")
    @JoinColumn(name = "Doc_Id")
    private Doctor doctor;
    // Constructors
    
    public DoctorContact() {

    }
    
    public DoctorContact(String contact, Doctor doctor) {
        super(contact);
        this.doctor = doctor;
        this.id = new DoctorContactId(doctor.getId(), contact);
    }
    
    // Getters and Setters
    
    public DoctorContactId getId() {
        return id;
    }
    
    public void setId(DoctorContactId id) {
        this.id = id;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
