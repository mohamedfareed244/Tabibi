package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.UserAcc;

public interface UserAccRepository extends JpaRepository<UserAcc,Integer> {
       // UserAcc findByEmail(String email);
   // List<UserAcc> findByName(String name);
   UserAcc findByEmail(String email);
   UserAcc findByUid(int uid);


    // UserAcc findByEmail(Object attribute);
}
