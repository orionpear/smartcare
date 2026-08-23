package lk.ac.sltc.ccs1303.smartcare.dto;

import java.time.LocalDate;

public record DepartmentHeadRequest(Long doctorId, LocalDate startDate) {
}