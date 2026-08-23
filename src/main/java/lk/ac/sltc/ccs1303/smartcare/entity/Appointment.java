package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Ap_Date")
    private LocalDate date;
    
    @Column(name = "Ap_Time")
    private LocalTime time;
    
    @Column(name = "Status")
    private String status;
    
    @Column(name = "Consult_Room")
    private String consultRoom;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "P_Id", nullable = false)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Doc_Id", nullable = false)
    private Doctor doctor;
    
    // Note: No more hard deletes. Instead, update status to "Canceled".
    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Treatment> treatments = new ArrayList<>();
    
    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<LabTest> tests = new ArrayList<>();
    
    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<Admission> admissions = new ArrayList<>();
    
    @OneToOne(mappedBy = "appointment")
    private Bill bill;
    // Constructors
    
    public Appointment() {
    
    }
    
    public Appointment(Long id) {
        this.id = id;
    }
    
    public Appointment(Long id, LocalDate date, LocalTime time, String status, String consultRoom) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.status = status;
        this.consultRoom = consultRoom;
    }
    
    public Appointment(Long id, LocalDate date, LocalTime time, String status, String consultRoom, Patient patient, Doctor doctor) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.status = status;
        this.consultRoom = consultRoom;
        this.patient = patient;
        this.doctor = doctor;
    }
    
    // Getters and Setters
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public String getConsultRoom() {
        return consultRoom;
    }
    
    public void setConsultRoom(String consultRoom) {
        this.consultRoom = consultRoom;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
