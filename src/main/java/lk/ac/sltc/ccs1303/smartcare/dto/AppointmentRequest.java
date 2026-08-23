package lk.ac.sltc.ccs1303.smartcare.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequest(Long doctorId, Long patientId, LocalDate date, LocalTime time, String consultRoom) {
}