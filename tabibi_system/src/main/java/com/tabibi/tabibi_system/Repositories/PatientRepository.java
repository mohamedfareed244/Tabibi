package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> 
{

    
}
