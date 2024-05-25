package com.tabibi.tabibi_system.Controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import junit.framework.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;

import java.util.Properties;

import org.junit.Before; 
   
// @RunWith(SpringRunner.class)
// @SpringBootTest
// @TestPropertySource(properties = "management.health.mail.enabled=false")

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest
 {

@Mock
public UserAccRepository userAccRepository;
 @Mock
 public JavaMailSenderImpl mailSender;
 
public UserController u;
    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);

        u = new UserController(userAccRepository, mailSender);

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


    @Test
        public void testSendToken() throws Exception {
            String email = "test@example.com";
            UserAcc mockUser = new UserAcc();
             Properties prop= new Properties();

            mockUser.setEmail(email);
            mockUser.setToken("1234");
           
            Mockito.when(userAccRepository.findByEmail(any())).thenReturn(mockUser);
        Mockito.when(mailSender.getJavaMailProperties()).thenReturn(prop);
         Mockito.when(userAccRepository.save(any())).thenReturn(mockUser);
            // Mock MimeMessage
            MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
            Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
           
            // Call the method under test
            u.send_token(email);
    
            // Verify that the token is set and saved
            Mockito.verify(userAccRepository).save(mockUser);
            Mockito.verify(userAccRepository).findByEmail(email);
            Assert.assertEquals(mockUser.getToken(), mockUser.getToken());
    
            // Verify that an email is sent
            Mockito.verify(mailSender).createMimeMessage();
            Mockito.verify(mailSender).send(mimeMessage);
    }


}
    