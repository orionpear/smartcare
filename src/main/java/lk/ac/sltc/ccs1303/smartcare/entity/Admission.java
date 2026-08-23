package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ad_Id")
    private Long id;
    
    @Column(name = "Ad_Date")
    private LocalDate admissionDate;
    
    @Column(name = "Dis_Date")
    private LocalDate dischargeDate;
    
    @Column(name = "Ad_Status")
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ap_Id", nullable = false)
    private Appointment appointment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "Room_Id", referencedColumnName = "Room_Id"),
            @JoinColumn(name = "Bed_Num", referencedColumnName = "Bed_Num")
    })
    private Bed bed;
    
    
    // Constructors
    
    public Admission() {
    }
    
    public Admission(Long id) {
        this.id = id;
    }
    
    public Admission(Long id, LocalDate admissionDate, LocalDate dischargeDate, String status, Appointment appointment) {
        this.id = id;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.status = status;
        this.appointment = appointment;
    }
    
    public Admission(Long id, LocalDate admissionDate, LocalDate dischargeDate, String status, Appointment appointment, Bed bed) {
        this.id = id;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.status = status;
        this.appointment = appointment;
        this.bed = bed;
    }
    
    // Getters and Setters
    
    public Bed getBed() {
        return bed;
    }
    
    public void setBed(Bed bed) {
        this.bed = bed;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getAdmissionDate() {
        return admissionDate;
    }
    
    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }
    
    public LocalDate getDischargeDate() {
        return dischargeDate;
    }
    
    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Appointment getAppointment() {
        return appointment;
    }
    
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
