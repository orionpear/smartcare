package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.Bed;
import lk.ac.sltc.ccs1303.smartcare.entity.RoomAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<Bed, RoomAssignmentId> {
}
