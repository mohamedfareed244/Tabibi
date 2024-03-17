package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.UserAcc;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{
            Doctor  findByUserAcc(UserAcc userAcc);

}
