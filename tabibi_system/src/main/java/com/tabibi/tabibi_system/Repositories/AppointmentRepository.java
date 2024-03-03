package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
    
}
