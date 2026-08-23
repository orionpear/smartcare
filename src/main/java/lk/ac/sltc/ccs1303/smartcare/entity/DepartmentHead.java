package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DepartmentHead {
    @Id
    @OneToOne
    @MapsId // Tells this class not to generate its own id
    @JoinColumn(name = "Dep_Id")
    private Department department;
    
    @OneToOne
    @JoinColumn(name = "Doc_Id", nullable = false)
    private Doctor doctor;
    
    @Column(name = "Start_Date")
    private LocalDate startDate;
    
    
    // Constructors
    
    public DepartmentHead() {
    }
    
    public DepartmentHead(Department department, Doctor doctor, LocalDate startDate) {
        this.department = department;
        this.doctor = doctor;
        this.startDate = startDate;
    }
    
    
    // Getters and Setters
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    // equals() and hashCode()
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentHead that = (DepartmentHead) o;
        return Objects.equals(department, that.department) && Objects.equals(doctor, that.doctor) && Objects.equals(startDate, that.startDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(department, doctor, startDate);
    }
}
