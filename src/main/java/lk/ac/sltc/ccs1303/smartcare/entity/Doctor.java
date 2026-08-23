package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "Doc_Id"))
public class Doctor extends Person{
    
    @Column(name = "Doc_Name")
    private String name;
    
    @Column(name = "Consult_Fee")
    private Double consultFee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Dep_Id", nullable = false)
    private Department department;
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorContact> contacts = new ArrayList<>();
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorQualification> qualifications = new ArrayList<>();
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorSpecialization> specializations = new ArrayList<>();
    
    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Appointment> appointments = new ArrayList<>();
    
    // Constructors
    
    public Doctor() {
    
    }
    
    public Doctor(Long id) {
        super(id);
    }
    
    public Doctor(Long id, String name, Double consultFee) {
        super(id);
        this.name = name;
        this.consultFee = consultFee;
    }
    
    public Doctor(Long id, String name, Double consultFee, Department department, List<DoctorContact> contacts, List<DoctorQualification> qualifications, List<DoctorSpecialization> specializations, List<Appointment> appointments) {
        super(id);
        this.name = name;
        this.consultFee = consultFee;
        this.department = department;
        this.contacts = contacts;
        this.qualifications = qualifications;
        this.specializations = specializations;
        this.appointments = appointments;
    }
    // Getters and Setters
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getConsultFee() {
        return consultFee;
    }
    
    public void setConsultFee(Double consultFee) {
        this.consultFee = consultFee;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public List<DoctorContact> getContacts() {
        return contacts;
    }
    
    public void setContacts(List<DoctorContact> contacts) {
        this.contacts = contacts;
    }
    
    public List<DoctorQualification> getQualifications() {
        return qualifications;
    }
    
    public void setQualifications(List<DoctorQualification> qualifications) {
        this.qualifications = qualifications;
    }
    
    public List<DoctorSpecialization> getSpecializations() {
        return specializations;
    }
    
    public void setSpecializations(List<DoctorSpecialization> specializations) {
        this.specializations = specializations;
    }
    
    public List<Appointment> getAppointments() {
        return appointments;
    }
    
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    // Override the getDisplayName method of the superclass Person
    
    @Override
    public String getDisplayName() {
        return name;
    }
}
