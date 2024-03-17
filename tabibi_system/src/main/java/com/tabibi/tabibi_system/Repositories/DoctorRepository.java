package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.UserAcc;

<<<<<<< HEAD
public interface DoctorRepository extends JpaRepository<Doctor,Long>
{
     List<Doctor> findByfirstname(String firstname);
    
=======
public interface DoctorRepository extends JpaRepository<Doctor,Long>{
            Doctor  findByUserAcc(UserAcc userAcc);

>>>>>>> 9d75fc01df146704cafdab4be8c9670437234653
}
