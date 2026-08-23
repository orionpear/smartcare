package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.DoctorSpecialization;
import lk.ac.sltc.ccs1303.smartcare.entity.DoctorSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, DoctorSpecializationId> {
}
