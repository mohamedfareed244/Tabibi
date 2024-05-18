package com.tabibi.tabibi_system.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Admin;


public interface AdminRepository extends JpaRepository<Admin,Integer> {
    
}
