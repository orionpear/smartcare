package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Room")
public class Bed {
    @EmbeddedId
    private RoomAssignmentId id;
    
    @OneToMany(mappedBy = "bed", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Admission> admissions = new ArrayList<>();
    
    // Constructors
    
    public Bed() {
    }
    
    public Bed(RoomAssignmentId id) {
        this.id = id;
    }
    
    public Bed(RoomAssignmentId id, List<Admission> admissions) {
        this.id = id;
        this.admissions = admissions;
    }
    
    // Getters and Setters
    
    public RoomAssignmentId getId() {
        return id;
    }
    
    public void setId(RoomAssignmentId id) {
        this.id = id;
    }
    
    public List<Admission> getAdmissions() {
        return admissions;
    }
    
    public void setAdmissions(List<Admission> admissions) {
        this.admissions = admissions;
    }
}
