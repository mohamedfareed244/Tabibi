package com.tabibi.tabibi_system.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tabibi.tabibi_system.Models.User;
import java.util.*;
import com.tabibi.tabibi_system.Controllers.*;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer>
{
    User findByUsername(String username);
    // List<User> findByName(String name);
}