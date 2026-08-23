package lk.ac.sltc.ccs1303.smartcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Department {
    // Let Spring know Dep_Id as the primary key and that it is auto incremented inside the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Dep_Id")
    private Long id;
    
    @Column(name = "Dep_Name")
    private String name;
    
    @Column(name = "Location")
    private String location;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Doctor> doctors;
    
    // Constructors
    
    public Department() {
    
    }
    
    public Department(Long id) {
        this.id = id;
    }
    
    public Department(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
    
    public Department(Long id, String name, String location, List<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.doctors = doctors;
    }
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public List<Doctor> getDoctors() {
        return doctors;
    }
    
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
    
    
    // equals() and hashCode()
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(doctors, that.doctors);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, doctors);
    }
}
