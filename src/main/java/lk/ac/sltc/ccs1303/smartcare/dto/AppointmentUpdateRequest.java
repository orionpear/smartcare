package lk.ac.sltc.ccs1303.smartcare.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentUpdateRequest(LocalDate date, LocalTime time, String consultRoom) {
}