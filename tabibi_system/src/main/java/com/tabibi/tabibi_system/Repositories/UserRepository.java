package com.tabibi.tabibi_system.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tabibi.tabibi_system.Model.User;
import java.util.*;
import com.tabibi.tabibi_system.Controller.*;

public interface UserRepository extends JpaRepository<User,Integer>
{
    User findByUsername(String username);
    List<User> findByName(String name);
}