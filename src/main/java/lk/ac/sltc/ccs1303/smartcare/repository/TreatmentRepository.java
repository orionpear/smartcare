package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import lk.ac.sltc.ccs1303.smartcare.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByAppointment(Appointment appointment);
    
    List<Treatment> findByAppointment_PatientOrderByDateDesc(Patient patient);
}
