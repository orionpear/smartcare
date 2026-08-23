package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
