package com.tabibi.tabibi_system.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        // Configure mock behavior for patient_repo
        when(patient_repo.findByfirstname("abcdef")).thenReturn(Collections.emptyList());

        // Test when no user is recorded with the name "abcdef"
        String value = Drcontroller.getData("abcdef");
        assertEquals("no patients found ", value);
    }

    @Test 
    public void testfoundeduser(){
        // Prepare test data
        Patient patient = new Patient();
        patient.setUid(1);
        patient.setFirstname("mohamed");

        // Configure mock behavior for patient_repo
        when(patient_repo.findByfirstname("mohamed")).thenReturn(Arrays.asList(patient));

        // Test when users are recorded with the name "mohamed"
        String value = Drcontroller.getData("mohamed");
        assertEquals("<tr onclick='edit(1)'><td>1</td><td>mohamed</td><td>null</td><td>null</td> </tr>", value); 
    }
}
