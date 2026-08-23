package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.Admission;
import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import lk.ac.sltc.ccs1303.smartcare.entity.Bed;
import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    boolean existsByBedAndDischargeDateIsNull(Bed bed);
    
    Optional<Admission> findByBedAndDischargeDateIsNull(Bed bed);
    
    List<Admission> findByAppointment_Patient(Patient patient);
    
    List<Admission> findByAppointment(Appointment appointment);
}
