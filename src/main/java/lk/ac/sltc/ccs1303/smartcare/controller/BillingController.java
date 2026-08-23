package lk.ac.sltc.ccs1303.smartcare.controller;

import lk.ac.sltc.ccs1303.smartcare.dto.BillRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.MarkPaidRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.PaymentMethodRequest;
import lk.ac.sltc.ccs1303.smartcare.dto.PaymentStatusRequest;
import lk.ac.sltc.ccs1303.smartcare.entity.Bill;
import lk.ac.sltc.ccs1303.smartcare.service.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillingController {
    
    private final BillingService billingService;
    
    // Constructors
    
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }
    
    // --- CRUD methods ---
    
    // Generate Bill
    @PostMapping
    public ResponseEntity<Bill> generateBill(@RequestBody BillRequest request) {
        Bill bill = billingService.generateBill(request.appointmentId(), request.paymentMethod());
        return ResponseEntity.status(HttpStatus.CREATED).body(bill);
    }
    
    // Update Payment Status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Bill> updatePaymentStatus(@PathVariable Long id,
                                                    @RequestBody PaymentStatusRequest request) {
        return ResponseEntity.ok(billingService.updatePaymentStatus(id, request.status()));
    }
    
    // Update Payment Method
    @PatchMapping("/{id}/payment-method")
    public ResponseEntity<Bill> updatePaymentMethod(@PathVariable Long id,
                                                    @RequestBody PaymentMethodRequest request) {
        return ResponseEntity.ok(billingService.updatePaymentMethod(id, request.paymentMethod()));
    }
    
    // Mark as Paid
    @PatchMapping("/{id}/pay")
    public ResponseEntity<Bill> markAsPaid(@PathVariable Long id, @RequestBody MarkPaidRequest request) {
        return ResponseEntity.ok(billingService.markAsPaid(id, request.paymentMethod()));
    }
    
    // --- Views ---
    
    // Get All
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return ResponseEntity.ok(billingService.getAllBills());
    }
    
    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getById(id));
    }
}