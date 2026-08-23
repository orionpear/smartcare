package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import lk.ac.sltc.ccs1303.smartcare.entity.LabTest;
import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Long> {
    List<LabTest> findByAppointment(Appointment appointment);
    
    List<LabTest> findByAppointment_Patient(Patient patient);
}
