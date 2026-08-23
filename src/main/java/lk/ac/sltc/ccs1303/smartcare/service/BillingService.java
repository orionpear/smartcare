package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.*;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.BillRepository;
import lk.ac.sltc.ccs1303.smartcare.repository.RoomCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BillingService {
    
    // Does not change for the rest of the application
    private static final String UNPAID_STATUS = "Unpaid";
    private static final String PAID_STATUS = "Paid";
    
    private final BillRepository billRepository;
    private final RoomCategoryRepository roomCategoryRepository;
    private final AppointmentService appointmentService;
    private final TreatmentService treatmentService;
    private final LabTestService labTestService;
    private final AdmissionService admissionService;
    
    // Constructors
    
    public BillingService(BillRepository billRepository,
                          RoomCategoryRepository roomCategoryRepository,
                          AppointmentService appointmentService,
                          TreatmentService treatmentService,
                          LabTestService labTestService,
                          AdmissionService admissionService) {
        this.billRepository = billRepository;
        this.roomCategoryRepository = roomCategoryRepository;
        this.appointmentService = appointmentService;
        this.treatmentService = treatmentService;
        this.labTestService = labTestService;
        this.admissionService = admissionService;
    }
    
    // --- CRUD methods ---
    
    // Generate Bill
    public Bill generateBill(Long appointmentId, String paymentMethod) {
        Appointment appointment = appointmentService.getById(appointmentId);
        
        Double total = calculateTotal(appointmentId);
        
        Bill bill = new Bill();
        bill.setAppointment(appointment);
        bill.setDate(LocalDate.now());
        bill.setPaymentMethod(paymentMethod);
        bill.setTotalAmount(total);
        bill.setStatus(UNPAID_STATUS);
        
        return billRepository.save(bill);
    }
    
    // Update Payment Status
    public Bill updatePaymentStatus(Long id, String status) {
        Bill bill = getById(id);
        bill.setStatus(status);
        return billRepository.save(bill);
    }
    
    // Update Payment Method
    public Bill updatePaymentMethod(Long id, String paymentMethod) {
        Bill bill = getById(id);
        bill.setPaymentMethod(paymentMethod);
        return billRepository.save(bill);
    }
    
    // Mark as Paid
    public Bill markAsPaid(Long id, String paymentMethod) {
        Bill bill = getById(id);
        bill.setStatus(PAID_STATUS);
        bill.setPaymentMethod(paymentMethod);
        return billRepository.save(bill);
    }
    
    // --- Views ---
    
    // Get by Id
    public Bill getById(Long id) {
        return billRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Bill Not Found: " + id));
    }
    
    // Get All
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
    
    // --- Helpers ---
    
    // Calculate Total: Consultation Fee + Treatments + Lab Tests + Admissions
    private Double calculateTotal(Long appointmentId) {
        Appointment appointment = appointmentService.getById(appointmentId);
        
        double consultFee = appointment.getDoctor().getConsultFee() != null
                                    ? appointment.getDoctor().getConsultFee() : 0.0;
        
        double treatmentTotal = treatmentService.getTreatmentsForAppointment(appointmentId).stream()
                                        .mapToDouble(t -> t.getTreatFee() != null ? t.getTreatFee() : 0.0)
                                        .sum();
        
        double labTestTotal = labTestService.getTestsForAppointment(appointmentId).stream()
                                      .mapToDouble(l -> l.getTestFee() != null ? l.getTestFee() : 0.0)
                                      .sum();
        
        double admissionTotal = admissionService.getAdmissionsForAppointment(appointmentId).stream()
                                        .mapToDouble(this::calculateAdmissionCharge)
                                        .sum();
        
        return consultFee + treatmentTotal + labTestTotal + admissionTotal;
    }
    
    // Calculate Admission Charge: Room Charge * Length of Stay
    private Double calculateAdmissionCharge(Admission admission) {
        Long roomId = admission.getBed().getId().getRoomId();
        RoomCategory roomCategory = roomCategoryRepository.findById(roomId)
                                            .orElseThrow(() -> new ResourceNotFoundException("Error: Room Category Not Found for room: " + roomId));
        
        LocalDate start = admission.getAdmissionDate();
        LocalDate end = admission.getDischargeDate() != null ? admission.getDischargeDate() : LocalDate.now();
        
        long days = ChronoUnit.DAYS.between(start, end);
        if (days < 1) {
            days = 1; // minimum one day charged, even for same-day admission/discharge
        }
        
        return roomCategory.getCharge() * days;
    }
}