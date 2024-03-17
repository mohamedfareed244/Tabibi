package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Pages;
import com.tabibi.tabibi_system.Models.UserTypePages;

public interface UserTypePagesRepository extends JpaRepository<UserTypePages,Long>
{
        List<UserTypePages> findByupid(Long type);

}
