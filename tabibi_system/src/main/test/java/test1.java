package com.tabibi.tabibi_system;

import org.junit.Test;
import com.tabibi.tabibi_system.Controllers.UserController;
import org.assertj.core.api.Assertions.assertThat;


public class test1 {
    @Test
    void testpassword(){
        UserController user=new UserController();
        
        
        assertTrue(user.loginprocess("abdelrahman","123"));
        assertFalse();

    }
    private boolean isPasswordValid(String password) {
        return password.length() > 8;
    }
}
