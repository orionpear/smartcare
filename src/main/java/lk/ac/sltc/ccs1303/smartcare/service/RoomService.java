package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.Bed;
import lk.ac.sltc.ccs1303.smartcare.entity.RoomAssignmentId;
import lk.ac.sltc.ccs1303.smartcare.entity.RoomCategory;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.BedRepository;
import lk.ac.sltc.ccs1303.smartcare.repository.RoomCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    
    private final RoomCategoryRepository roomCategoryRepository;
    private final BedRepository bedRepository;
    
    // Constructors
    
    public RoomService(RoomCategoryRepository roomCategoryRepository, BedRepository bedRepository) {
        this.roomCategoryRepository = roomCategoryRepository;
        this.bedRepository = bedRepository;
    }
    
    // --- Room Category CRUD ---
    
    // Add
    public RoomCategory addRoomCategory(Long id, String category, Double charge) {
        RoomCategory roomCategory = new RoomCategory(id, category, charge);
        return roomCategoryRepository.save(roomCategory);
    }
    
    // Update
    public RoomCategory updateRoomCategory(Long id, String category, Double charge) {
        RoomCategory roomCategory = getRoomCategoryById(id);
        roomCategory.setCategory(category);
        roomCategory.setCharge(charge);
        return roomCategoryRepository.save(roomCategory);
    }
    
    // Delete
    public void deleteRoomCategory(Long id) {
        RoomCategory roomCategory = getRoomCategoryById(id);
        roomCategoryRepository.delete(roomCategory);
    }
    
    // Get by Id
    public RoomCategory getRoomCategoryById(Long id) {
        return roomCategoryRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Room Category Not Found: " + id));
    }
    
    // Get All
    public List<RoomCategory> getAllRoomCategories() {
        return roomCategoryRepository.findAll();
    }
    
    // --- Bed Management ---
    
    // Add
    public Bed addBed(Long roomId, Long bedNum) {
        getRoomCategoryById(roomId);
        Bed bed = new Bed(new RoomAssignmentId(roomId, bedNum));
        return bedRepository.save(bed);
    }
    
    // Delete
    public void deleteBed(Long roomId, Long bedNum) {
        Bed bed = getBed(roomId, bedNum);
        bedRepository.delete(bed);
    }
    
    // Get Bed
    public Bed getBed(Long roomId, Long bedNum) {
        RoomAssignmentId bedId = new RoomAssignmentId(roomId, bedNum);
        return bedRepository.findById(bedId)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Bed Not Found: Room " + roomId + ", Bed " + bedNum));
    }
    
    // Get All
    public List<Bed> getAllBeds() {
        return bedRepository.findAll();
    }
    
    // Get Available
    public List<Bed> getAvailableBeds() {
        return bedRepository.findAvailableBeds();
    }
}