package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    
}
