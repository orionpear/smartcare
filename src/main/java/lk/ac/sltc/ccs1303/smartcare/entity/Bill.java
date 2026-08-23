package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Bill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bill_Id")
    private Long id;
    
    @Column(name = "Bill_Date")
    private LocalDate date;
    
    @Column(name = "Pay_Status")
    private String status;
    
    @Column(name = "Pay_Method")
    private String paymentMethod;
    
    @Column(name = "Total_Amount")
    private Double totalAmount;
    
    @OneToOne
    @JoinColumn(name = "Ap_Id")
    private Appointment appointment;
    
    
    // Constructors
    
    public Bill() {
    }
    
    public Bill(Long id) {
        this.id = id;
    }
    
    public Bill(Long id, LocalDate date, String status, String paymentMethod, Double totalAmount, Appointment appointment) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
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
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Appointment getAppointment() {
        return appointment;
    }
    
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
