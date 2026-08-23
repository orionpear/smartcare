package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.BedRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.RoomCategoryRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.RoomCategoryUpdateRequest;
import lk.ac.sltc.ccs1303.smartcare.entity.Bed;
import lk.ac.sltc.ccs1303.smartcare.entity.RoomCategory;
import lk.ac.sltc.ccs1303.smartcare.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    
    private final RoomService roomService;
    
    // Constructors
    
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    
    // --- Room Category CRUD ---
    
    // Add
    @PostMapping("/categories")
    public ResponseEntity<RoomCategory> addRoomCategory(@RequestBody RoomCategoryRequest request) {
        RoomCategory category = roomService.addRoomCategory(request.id(), request.category(), request.charge());
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    
    // Update
    @PutMapping("/categories/{id}")
    public ResponseEntity<RoomCategory> updateRoomCategory(@PathVariable Long id,
                                                           @RequestBody RoomCategoryUpdateRequest request) {
        return ResponseEntity.ok(roomService.updateRoomCategory(id, request.category(), request.charge()));
    }
    
    // Delete
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteRoomCategory(@PathVariable Long id) {
        roomService.deleteRoomCategory(id);
        return ResponseEntity.noContent().build();
    }
    
    // Get All
    @GetMapping("/categories")
    public ResponseEntity<List<RoomCategory>> getAllRoomCategories() {
        return ResponseEntity.ok(roomService.getAllRoomCategories());
    }
    
    // Get by Id
    @GetMapping("/categories/{id}")
    public ResponseEntity<RoomCategory> getRoomCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomCategoryById(id));
    }
    
    // --- Bed Management ---
    
    // Add
    @PostMapping("/beds")
    public ResponseEntity<Bed> addBed(@RequestBody BedRequest request) {
        Bed bed = roomService.addBed(request.roomId(), request.bedNum());
        return ResponseEntity.status(HttpStatus.CREATED).body(bed);
    }
    
    // Delete
    @DeleteMapping("/beds/{roomId}/{bedNum}")
    public ResponseEntity<Void> deleteBed(@PathVariable Long roomId, @PathVariable Long bedNum) {
        roomService.deleteBed(roomId, bedNum);
        return ResponseEntity.noContent().build();
    }
    
    // Get All
    @GetMapping("/beds")
    public ResponseEntity<List<Bed>> getAllBeds() {
        return ResponseEntity.ok(roomService.getAllBeds());
    }
    
    // Get Available
    @GetMapping("/beds/available")
    public ResponseEntity<List<Bed>> getAvailableBeds() {
        return ResponseEntity.ok(roomService.getAvailableBeds());
    }
}