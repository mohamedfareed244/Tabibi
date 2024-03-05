package com.tabibi.tabibi_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.UserTypes;

public interface UserTypeRepository extends JpaRepository<UserTypes,Long>

{
    
}
