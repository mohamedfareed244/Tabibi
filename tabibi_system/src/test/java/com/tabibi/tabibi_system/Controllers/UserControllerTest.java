package com.tabibi.tabibi_system.Controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import junit.framework.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Before; 
   
// @RunWith(SpringRunner.class)
// @SpringBootTest
// @TestPropertySource(properties = "management.health.mail.enabled=false")

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class UserControllerTest
 {
        @Autowired
    private MockMvc mockMvc;

@Mock
public Doctor doctor;
@Mock
public Model model;
@Mock 
public DoctorRepository doctorRepository;
@Mock
public UserAccRepository userAccRepository;
 @Mock
 public JavaMailSenderImpl mailSender;
 
public UserController u;
    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);

        u = new UserController(userAccRepository, mailSender,doctorRepository);

    }
        
    @Test
    public void passwordtest() 
    {
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

    public boolean validateEmail(String email) 
    {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regexPattern);
     }

 @Test
    public void testSearchPost() throws Exception 
    {
        
    Doctor alyDoctor=new Doctor("aly", "arafat", "ozon", "01551564342", "MIU");
    Doctor mockDoctor=new Doctor("John", "mai", "kela", "01551564342", "MIU");
    List<Doctor> doctors=new ArrayList<>();
    doctors.add(alyDoctor);
    doctors.add(mockDoctor);
    
    when(model.addAttribute(any(), any())).thenReturn(null);

    MockedStatic mockedclass=  Mockito.mockStatic(admincontroller.class);

    mockedclass.when(()->admincontroller.preparenavigation(any(), any(), any(), any())).thenReturn(new ModelAndView());

    when(doctorRepository.findByspecialization("ozon")).thenReturn(doctors);

    u.searchresult("ozon",model, null);

    Mockito.verify(doctorRepository).findByspecialization(anyString());
    mockedclass.verify(()->admincontroller.preparenavigation(any(),any(),any(),any()));

    }


    @Test
        public void testSendToken() throws Exception 
        {
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

    
            // Verify that an email is sent
            Mockito.verify(mailSender).createMimeMessage();
            Mockito.verify(mailSender).send(mimeMessage);
    }
@Test
public void hash_passwordtest()
{
String encoddedPassword=new String();   
u.hashpassword(encoddedPassword);
}

// @Test
// public void ChangePassword_Test()
// {   
// UserAcc checkAcc = mock(UserAcc.class);;
// String newpass="arafat";
// String conifrmpass="arafat";
// String Token="2123";
// when(userAccRepository.findByUid(1)).thenReturn(checkAcc);
// u.ChangePassword(newpass, conifrmpass, Token);
// }

}
    