package lk.ac.sltc.ccs1303.smartcare.dto;

import java.time.LocalDate;

public record PatientRequest(String firstName, String lastName, String address, String blood, LocalDate dob) {
}