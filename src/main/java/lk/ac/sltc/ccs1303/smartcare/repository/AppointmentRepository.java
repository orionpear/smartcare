package lk.ac.sltc.ccs1303.smartcare.repository;

import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import lk.ac.sltc.ccs1303.smartcare.entity.Doctor;
import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    boolean existsByDoctorAndDateAndTimeAndStatusNot(Doctor doctor, LocalDate date, LocalTime time, String status);
    
    boolean existsByDoctorAndDateAndTimeAndStatusNotAndIdNot(Doctor doctor, LocalDate date, LocalTime time, String status, Long id);
    
    List<Appointment> findByDoctorAndDateOrderByTime(Doctor doctor, LocalDate date);
    
    List<Appointment> findByPatient(Patient patient);
    
    List<Appointment> findByDoctor(Doctor doctor);
}