package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.UserAcc;

public interface UserAccRepository extends JpaRepository<UserAcc,Long> {
    
}
