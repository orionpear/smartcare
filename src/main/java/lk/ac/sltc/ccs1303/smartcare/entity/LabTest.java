package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Test_Name")
    private String testName;
    
    @Column(name = "Test_Date")
    private LocalDate date;
    
    @Column(name = "Test_Status")
    private String status;
    
    @Column(name = "Tech_Name")
    private String techName;
    
    @Column(name = "Test_Result")
    private String testResult;
    
    @Column(name = "Test_Fee")
    private Double testFee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ap_Id", nullable = false)
    private Appointment appointment;
    
    
    // Constructors
    
    public LabTest() {
    }
    
    public LabTest(Long id, String testName, LocalDate date, String status, String techName, String testResult, Double testFee, Appointment appointment) {
        this.id = id;
        this.testName = testName;
        this.date = date;
        this.status = status;
        this.techName = techName;
        this.testResult = testResult;
        this.testFee = testFee;
        this.appointment = appointment;
    }
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTestName() {
        return testName;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
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
    
    public String getTechName() {
        return techName;
    }
    
    public void setTechName(String techName) {
        this.techName = techName;
    }
    
    public String getTestResult() {
        return testResult;
    }
    
    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
    
    public Appointment getAppointment() {
        return appointment;
    }
    
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    public Double getTestFee() {
        return testFee;
    }
    
    public void setTestFee(Double testFee) {
        this.testFee = testFee;
    }
}
