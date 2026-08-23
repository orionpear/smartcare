package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    // Constructors
    public Person() {
    
    }
    
    public Person(Long id) {
        this.id = id;
    }
    
    // Getters and Setters
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public abstract String getDisplayName();
}
