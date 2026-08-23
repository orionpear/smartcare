package lk.ac.sltc.ccs1303.smartcare.service;

import lk.ac.sltc.ccs1303.smartcare.entity.Appointment;
import lk.ac.sltc.ccs1303.smartcare.entity.Doctor;
import lk.ac.sltc.ccs1303.smartcare.entity.Patient;
import lk.ac.sltc.ccs1303.smartcare.exception.ResourceNotFoundException;
import lk.ac.sltc.ccs1303.smartcare.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {
    // Does not change for the rest of the application
    private static final String CANCELED_STATUS = "Canceled";
    private static final String SCHEDULED_STATUS = "Scheduled";
    
    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    
    // Constructors
    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorService doctorService,
                              PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }
    
    // --- CRUD methods ---
    
    // Book
    public Appointment bookAppointment(Long doctorId, Long patientId, LocalDate date, LocalTime time, String consultRoom) {
        Doctor doctor = doctorService.getById(doctorId);
        Patient patient = patientService.getById(patientId);
        
        if (appointmentRepository.existsByDoctorAndDateAndTimeAndStatusNot(doctor, date, time, CANCELED_STATUS)) {
            throw new IllegalStateException("Doctor already has an appointment at this date and time.");
        }
        
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setConsultRoom(consultRoom);
        appointment.setStatus(SCHEDULED_STATUS);
        
        return appointmentRepository.save(appointment);
    }
    
    // Update / Reschedule
    
    public Appointment updateAppointment(Long id, LocalDate date, LocalTime time, String consultRoom) {
        Appointment appointment = getById(id);
        
        boolean clash = appointmentRepository.existsByDoctorAndDateAndTimeAndStatusNotAndIdNot(
                appointment.getDoctor(), date, time, CANCELED_STATUS, id);
        if (clash) {
            throw new IllegalStateException("Doctor already has an appointment at this date and time.");
        }
        
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setConsultRoom(consultRoom);
        
        return appointmentRepository.save(appointment);
    }
    
    // Cancel
    
    public Appointment cancelAppointment(Long id) {
        Appointment appointment = getById(id);
        appointment.setStatus(CANCELED_STATUS);
        return appointmentRepository.save(appointment);
    }
    
    // --- Views ---
    
    // Get by Id
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                       .orElseThrow(() -> new ResourceNotFoundException("Error: Appointment Not Found: " + id));
    }
    
    // Get All
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    // Get Schedule
    public List<Appointment> getScheduleForDoctor(Long doctorId, LocalDate date) {
        Doctor doctor = doctorService.getById(doctorId);
        return appointmentRepository.findByDoctorAndDateOrderByTime(doctor, date);
    }
    
    // Get Appointments for Patient
    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        Patient patient = patientService.getById(patientId);
        return appointmentRepository.findByPatient(patient);
    }
    
    // Get Appointments for Doctor
    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
        Doctor doctor = doctorService.getById(doctorId);
        return appointmentRepository.findByDoctor(doctor);
    }
}
