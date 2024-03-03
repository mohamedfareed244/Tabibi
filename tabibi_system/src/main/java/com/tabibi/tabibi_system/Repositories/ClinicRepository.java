package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic,Long>{
    
}
