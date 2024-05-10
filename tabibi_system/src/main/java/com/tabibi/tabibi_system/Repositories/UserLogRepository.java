package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.UserLog;

public interface UserLogRepository extends JpaRepository<UserLog,Integer> {
  
}
