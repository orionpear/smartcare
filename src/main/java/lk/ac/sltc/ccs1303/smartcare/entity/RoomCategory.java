package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RoomCategory {
    @Id
    @Column(name = "Room_Id")
    private Long id;
    
    @Column(name = "Room_Category")
    private String category;
    
    @Column(name = "Room_Charge")
    private Double charge;
    
    // Constructors
    
    public RoomCategory() {
    }
    
    public RoomCategory(Long id, String category, Double charge) {
        this.id = id;
        this.category = category;
        this.charge = charge;
    }
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Double getCharge() {
        return charge;
    }
    
    public void setCharge(Double charge) {
        this.charge = charge;
    }
}