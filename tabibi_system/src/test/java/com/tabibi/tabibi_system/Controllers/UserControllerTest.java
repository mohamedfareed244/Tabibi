package com.tabibi.tabibi_system.Controllers;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;

import jakarta.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.nullable;

import org.junit.Before;    
    
public class UserControllerTest {
public UserController u;
@Autowired
public UserAccRepository repo;
public UserAccRepository userAccRepository;

    @Before
    public void setup(){
        userAccRepository = Mockito.mock(UserAccRepository.class);

        u = new UserController(userAccRepository);

    }
        
    @Test
    public void passwordtest() {
        String pass="12345678";
        String result=u.passtest(pass);
        assertEquals(8,result.length() );  
    }
    @Test
    public void emailtest() {
        String email="ptest@email.com";
        boolean result=validateEmail(email);
        assertEquals(true,result );  
    }
    public boolean validateEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regexPattern);
    }
}
    