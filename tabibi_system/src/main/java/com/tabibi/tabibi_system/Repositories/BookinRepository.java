package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Booking;

public interface BookinRepository extends JpaRepository <Booking,Long>{
    
}
