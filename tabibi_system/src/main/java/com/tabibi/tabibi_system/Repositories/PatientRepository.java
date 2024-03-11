package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.UserAcc;



public interface PatientRepository extends JpaRepository<Patient,Integer> 
{
    Patient  findByUserAcc(UserAcc userAcc);
    
}
