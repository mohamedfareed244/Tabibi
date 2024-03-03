package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
    
}
