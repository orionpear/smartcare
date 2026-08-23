package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.Doctor;
import lk.ac.sltc.ccs1303.smartcare.entity.DoctorQualification;
import lk.ac.sltc.ccs1303.smartcare.entity.DoctorQualificationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorQualificationRepository extends JpaRepository<DoctorQualification, DoctorQualificationId> {
}
