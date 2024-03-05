package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.pages;

public interface PagesRepository extends JpaRepository<pages,Long> {
    
}
