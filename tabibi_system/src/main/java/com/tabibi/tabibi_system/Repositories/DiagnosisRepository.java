package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Diagnosis;
import com.tabibi.tabibi_system.Models.UserAcc;

public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {

    List<Diagnosis> findByUserAcc(UserAcc userAcc);
    Diagnosis findByDiagnosisId(Long diagnosisId);
    List<Diagnosis> findByUserAccAndUser(UserAcc userAcc, UserAcc user);
    
}
