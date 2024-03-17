package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long>
{
     List<Doctor> findByfirstname(String firstname);
    
}
