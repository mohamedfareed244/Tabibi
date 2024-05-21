package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tabibi.tabibi_system.Models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

    Appointment findByappId(long appId);

    void deleteByappId(long appId);
   @Query("SELECT a FROM Appointment a WHERE a.doctor.specialization = :specialization")
    List<Appointment> findByDoctorSpecialization(@Param("specialization") String specialization);

  
        List<Appointment> findByStatus(String status);
        List<Appointment> findByDoctorSpecializationAndStatus(String specialization, String status);
    
    
    
}
