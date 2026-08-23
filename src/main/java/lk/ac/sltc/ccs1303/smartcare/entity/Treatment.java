package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Treatment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Treat_Date")
    private LocalDate date;
    
    @Column(name = "Diagnosis")
    private String diagnosis;
    
    @Column(name = "Presc_Details")
    private String prescriptionDetails;
    
    @Column(name = "Treat_Notes")
    private String treatmentNotes;
    
    @Column(name = "Treat_Fee")
    private Double treatFee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ap_Id", nullable = false)
    private Appointment appointment;
    
    
    // Constructors
    
    public Treatment() {
    
    }
    
    public Treatment(Long id) {
        this.id = id;
    }
    
    public Treatment(Long id, LocalDate date, String diagnosis, String prescriptionDetails, String treatmentNotes, Double treatFee, Appointment appointment) {
        this.id = id;
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescriptionDetails = prescriptionDetails;
        this.treatmentNotes = treatmentNotes;
        this.treatFee = treatFee;
        this.appointment = appointment;
    }
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }
    
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public String getPrescriptionDetails() {
        return prescriptionDetails;
    }
    
    public void setPrescriptionDetails(String prescriptionDetails) {
        this.prescriptionDetails = prescriptionDetails;
    }
    
    public String getTreatmentNotes() {
        return treatmentNotes;
    }
    
    public void setTreatmentNotes(String treatmentNotes) {
        this.treatmentNotes = treatmentNotes;
    }
    
    public Double getTreatFee() {
        return treatFee;
    }
    
    public void setTreatFee(Double treatFee) {
        this.treatFee = treatFee;
    }
    
    public Appointment getAppointment() {
        return appointment;
    }
    
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
