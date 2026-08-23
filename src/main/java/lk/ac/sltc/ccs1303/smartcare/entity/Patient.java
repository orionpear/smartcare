/**
 * This class maps to the Patient of the smartcare_db database.
 */

package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "P_Id"))
public class Patient extends Person{
    
    // Map columns of the database
    @Column(name = "F_Name")
    private String firstName;
    
    @Column(name = "L_Name")
    private String lastName;
    
    @Column(name = "Address")
    private String address;
    
    @Column(name = "Blood")
    private String blood;
    
    @Column(name = "DoB", nullable = false)
    private LocalDate DoB;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientContact> contacts = new ArrayList<>();
    
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Appointment> appointments = new ArrayList<>();
    
    // End mapping process
    
    // Constructors
    public Patient() {
    
    }
    
    public Patient(Long id) {
        super(id);
    }
    
    public Patient(Long id, String firstName, String lastName, String address, String blood, LocalDate doB) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.blood = blood;
        DoB = doB;
    }
    
    public Patient(Long id, String firstName, String lastName, String address, String blood, LocalDate doB, List<PatientContact> contacts, List<Appointment> appointments) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.blood = blood;
        DoB = doB;
        this.contacts = contacts;
        this.appointments = appointments;
    }
    
    // Getters and Setters
    
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public List<PatientContact> getContacts() {
        return contacts;
    }
    
    public void setContacts(List<PatientContact> contacts) {
        this.contacts = contacts;
    }
    
    public LocalDate getDoB() {
        return DoB;
    }
    
    public void setDoB(LocalDate doB) {
        DoB = doB;
    }
    
    public String getBlood() {
        return blood;
    }
    
    public void setBlood(String blood) {
        this.blood = blood;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public List<Appointment> getAppointments() {
        return appointments;
    }
    
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    // Override the getDisplayMethod from superclass Person
    @Override
    public String getDisplayName() {
        return firstName + " " + lastName;
    }
}
