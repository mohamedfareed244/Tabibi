package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.UserAcc;



public interface PatientRepository extends JpaRepository<Patient,Long> 
{
    Patient  findByUserAcc(UserAcc userAcc);

    // @Query("SELECT * FROM patient e WHERE WHERE CONCAT(firstName, ' ', lastName) LIKE %:substring%")
    // List<Patient> findBySubstring(String substring);
    
}
