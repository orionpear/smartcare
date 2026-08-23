package lk.ac.sltc.ccs1303.smartcare.dto;

public record TreatmentUpdateRequest(String diagnosis, String prescriptionDetails, String treatmentNotes,
                                     Double treatFee) {
}