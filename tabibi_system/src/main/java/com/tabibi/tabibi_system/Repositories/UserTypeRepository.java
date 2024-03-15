package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Pages;
import com.tabibi.tabibi_system.Models.UserTypes;

public interface UserTypeRepository extends JpaRepository<UserTypes,Long>
{

UserTypes findByname(String name);
    
}
