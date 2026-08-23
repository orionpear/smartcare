package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.Department;
import lk.ac.sltc.ccs1303.smartcare.entity.DepartmentHead;
import lk.ac.sltc.ccs1303.smartcare.entity.Doctor;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.DepartmentHeadRepository;
import lk.ac.sltc.ccs1303.smartcare.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    private final DepartmentHeadRepository departmentHeadRepository;
    
    public DepartmentService(DepartmentRepository departmentRepository,
                             DepartmentHeadRepository departmentHeadRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentHeadRepository = departmentHeadRepository;
    }
    
    // ---------- Core CRUD ----------
    
    // Add
    public Department addDepartment(String name, String location) {
        Department department = new Department();
        department.setName(name);
        department.setLocation(location);
        return departmentRepository.save(department);
    }
    
    // Update
    public Department updateDepartment(Long id, String name, String location) {
        Department department = getById(id);
        department.setName(name);
        department.setLocation(location);
        return departmentRepository.save(department);
    }
    
    // Delete
    public void deleteDepartment(Long id) {
        Department department = getById(id);
        departmentRepository.delete(department);
    }
    
    // Get All
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    // Get by Id
    public Department getById(Long id) {
        return departmentRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Department Not Found: " + id));
    }
    
    // --- Department Head ---
    
    // Assign
    public DepartmentHead assignHead(Long departmentId, Doctor doctor, LocalDate startDate) {
        Department department = getById(departmentId);
        DepartmentHead head = new DepartmentHead(department, doctor, startDate);
        return departmentHeadRepository.save(head);
    }
    
    // Update
    public DepartmentHead updateHead(Long departmentId, Doctor newDoctor, LocalDate startDate) {
        DepartmentHead head = getHead(departmentId);
        head.setDoctor(newDoctor);
        head.setStartDate(startDate);
        return departmentHeadRepository.save(head);
    }
    
    // Get
    public DepartmentHead getHead(Long departmentId) {
        return departmentHeadRepository.findById(departmentId)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Department Head Not Found for department: " + departmentId));
    }
}