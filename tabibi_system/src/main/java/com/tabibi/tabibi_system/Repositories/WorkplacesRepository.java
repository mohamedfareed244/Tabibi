package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Workplaces;

public interface WorkplacesRepository extends JpaRepository<Workplaces,Long> 
{
  List<Workplaces> findByClinic(Clinic clinic);
 boolean existsByDoctorAndClinic(Doctor doctor, Clinic clinic);   
}
