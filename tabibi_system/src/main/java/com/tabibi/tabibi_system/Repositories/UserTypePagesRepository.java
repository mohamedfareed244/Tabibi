package com.tabibi.tabibi_system.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.Pages;
import com.tabibi.tabibi_system.Models.UserTypePages;
import com.tabibi.tabibi_system.Models.UserTypes;

public interface UserTypePagesRepository extends JpaRepository<UserTypePages,Long>
{
        List<UserTypePages> findByupid(Long type);
        List<UserTypePages> findByUsertype(UserTypes usertype);
        void deleteByUsertype(UserTypes type);
        void deleteByupid(UserTypes id);

        

}
