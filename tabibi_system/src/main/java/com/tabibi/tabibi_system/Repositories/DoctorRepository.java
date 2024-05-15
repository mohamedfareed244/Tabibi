package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.UserAcc;


public interface DoctorRepository extends JpaRepository<Doctor,Integer>
{
     List<Doctor> findByspecialization(String firstname);
          //   Doctor  findByUserAcc(UserAcc userAcc);
}
