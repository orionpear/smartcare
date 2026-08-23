package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.DepartmentHeadRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.DepartmentRequest;
import lk.ac.sltc.ccs1303.smartcare.entity.Department;
import lk.ac.sltc.ccs1303.smartcare.entity.DepartmentHead;
import lk.ac.sltc.ccs1303.smartcare.entity.Doctor;
import lk.ac.sltc.ccs1303.smartcare.service.DepartmentService;
import lk.ac.sltc.ccs1303.smartcare.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    
    // Constructors
    
    public DepartmentController(DepartmentService departmentService, DoctorService doctorService) {
        this.departmentService = departmentService;
        this.doctorService = doctorService;
    }
    
    // --- Department CRUD ---
    
    // Add
    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody DepartmentRequest request) {
        Department department = departmentService.addDepartment(request.name(), request.location());
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }
    
    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
                                                       @RequestBody DepartmentRequest request) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, request.name(), request.location()));
    }
    
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
    
    // Get All
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    
    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }
    
    // --- Department Head ---
    
    // Assign Head
    @PostMapping("/{id}/head")
    public ResponseEntity<DepartmentHead> assignHead(@PathVariable Long id,
                                                     @RequestBody DepartmentHeadRequest request) {
        Doctor doctor = doctorService.getById(request.doctorId());
        DepartmentHead head = departmentService.assignHead(id, doctor, request.startDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(head);
    }
    
    // Update Head
    @PutMapping("/{id}/head")
    public ResponseEntity<DepartmentHead> updateHead(@PathVariable Long id,
                                                     @RequestBody DepartmentHeadRequest request) {
        Doctor doctor = doctorService.getById(request.doctorId());
        return ResponseEntity.ok(departmentService.updateHead(id, doctor, request.startDate()));
    }
    
    // Get Head
    @GetMapping("/{id}/head")
    public ResponseEntity<DepartmentHead> getHead(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getHead(id));
    }
}