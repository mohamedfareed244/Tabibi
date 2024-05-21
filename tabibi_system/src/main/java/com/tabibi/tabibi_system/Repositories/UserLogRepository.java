package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tabibi.tabibi_system.Models.UserLog;
@Repository
public interface UserLogRepository extends JpaRepository<UserLog,Integer> {
    public List<UserLog> findByUserIdOrderByLogDateDesc(int userId);
}
