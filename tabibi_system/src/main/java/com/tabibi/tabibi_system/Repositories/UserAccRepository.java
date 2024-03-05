package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.UserAcc;

public interface UserAccRepository extends JpaRepository<UserAcc,Long> {
        UserAcc findByUsername(String username);
    List<UserAcc> findByName(String name);
}
