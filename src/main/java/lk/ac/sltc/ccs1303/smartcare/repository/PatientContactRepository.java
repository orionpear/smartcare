package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.PatientContact;
import lk.ac.sltc.ccs1303.smartcare.entity.PatientContactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientContactRepository extends JpaRepository<PatientContact, PatientContactId> {
}
