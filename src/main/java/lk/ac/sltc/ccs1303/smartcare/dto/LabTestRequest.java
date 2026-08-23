package lk.ac.sltc.ccs1303.smartcare.dto;

import java.time.LocalDate;

public record LabTestRequest(Long appointmentId, String testName, LocalDate date, String techName, Double testFee) {
}