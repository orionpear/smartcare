package lk.ac.sltc.ccs1303.smartcare.dto;

import java.time.LocalDate;

public record AdmissionRequest(Long appointmentId, Long roomId, Long bedNum, LocalDate admissionDate) {
}