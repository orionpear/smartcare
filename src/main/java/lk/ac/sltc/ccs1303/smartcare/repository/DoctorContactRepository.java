package lk.ac.sltc.ccs1303.smartcare.repository;

import jakarta.persistence.Entity;
import lk.ac.sltc.ccs1303.smartcare.entity.DoctorContact;
import lk.ac.sltc.ccs1303.smartcare.entity.DoctorContactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorContactRepository extends JpaRepository<DoctorContact, DoctorContactId> {
}
