package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment,Long> {
    
}
