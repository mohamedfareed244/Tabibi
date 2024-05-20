package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tabibi.tabibi_system.Models.Pages;

public interface PagesRepository extends JpaRepository<Pages,Long> {
    Pages findByname(String name);
    Pages findBypgid(Long name);
}
