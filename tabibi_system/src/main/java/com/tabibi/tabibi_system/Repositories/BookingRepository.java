package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Appointment;
import com.tabibi.tabibi_system.Models.Booking;
import com.tabibi.tabibi_system.Models.Patient;

public interface BookingRepository extends JpaRepository <Booking,Long>{
    List<Booking> findByPatient(Patient patient);
    void deleteByAppointmentAppId(long appID);
   List<Booking> findByAppointmentAppId(long appId);
    
}
