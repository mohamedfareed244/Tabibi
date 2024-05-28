package com.tabibi.tabibi_system.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Repositories.PatientRepository;


public class DoctorControllerTest {

    
    @InjectMocks
    private doctorcontroller Drcontroller;

    private MockHttpSession session;

    @Mock
    private PatientRepository patient_repo;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        session = new MockHttpSession();
    }

    @Test 
    public void testusernotfound(){
        //no user recorded with the name abcdef 
        String value=this.Drcontroller.getData("abcdef");
        assertEquals("no patients found ", value);
    }

    @Test 
    public void testfoundeduser(){
         // there are users recorded with the name mohamed 
         String value=this.Drcontroller.getData("mohamed");
         System.out.println(value);
         assertEquals("no patients found ", value);
    }


}
