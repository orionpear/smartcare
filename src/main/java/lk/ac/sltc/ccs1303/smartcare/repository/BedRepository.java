package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.Bed;
import lk.ac.sltc.ccs1303.smartcare.entity.RoomAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedRepository extends JpaRepository<Bed, RoomAssignmentId> {
    @Query("SELECT b FROM Bed b WHERE b NOT IN " +
                   "(SELECT a.bed FROM Admission a WHERE a.dischargeDate IS NULL)")
    List<Bed> findAvailableBeds();
}
