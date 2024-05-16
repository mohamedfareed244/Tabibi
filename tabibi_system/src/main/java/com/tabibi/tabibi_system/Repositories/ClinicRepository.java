package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.UserAcc;

public interface ClinicRepository extends JpaRepository<Clinic,Integer>{
        // Clinic  findByUserAcc(UserAcc userAcc);
           Clinic findByEmail(String email);

    
}
