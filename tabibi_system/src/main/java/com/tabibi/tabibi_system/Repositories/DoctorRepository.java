package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{
    
}
