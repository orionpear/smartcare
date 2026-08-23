package lk.ac.sltc.ccs1303.smartcare.dto;

import java.time.LocalDate;

public record TreatmentRequest(Long appointmentId, LocalDate date, String diagnosis, String prescriptionDetails,
                               String treatmentNotes, Double treatFee) {
}