package lk.ac.sltc.ccs1303.smartcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoomAssignmentId implements Serializable {
    @Column(name = "Room_Id")
    private Long roomId;
    
    @Column(name = "Bed_Num")
    private Long bedNum;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        RoomAssignmentId that = (RoomAssignmentId) o;
        return Objects.equals(roomId, that.roomId) && Objects.equals(bedNum, that.bedNum);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(roomId, bedNum);
    }
    
    
    // Constructors
    public RoomAssignmentId () {}
    
    public RoomAssignmentId(Long roomId, Long bedNum) {
        this.roomId = roomId;
        this.bedNum = bedNum;
    }
    
    
    // Getters and Setters
    
    public Long getRoomId() {
        return roomId;
    }
    
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    
    public Long getBedNum() {
        return bedNum;
    }
    
    public void setBedNum(Long bedNum) {
        this.bedNum = bedNum;
    }
}
