package com.tabibi.tabibi_system.Controllers;

import org.junit.Test;
import org.mockito.Mockito;
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
    @Before
    public void setup(){
        u = new UserController();

    }
        
    @Test
    public void test() {
        
        String pass="12345678";
        String result=u.loginprocess(pass);
        assertEquals(8,result.length() );  
    }
}
    