package com.tabibi.tabibi_system.Controllers;

import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClinicControllerTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private UserTypeRepository user_type_repo;

    @Mock
    private UserTypePagesRepository page_type_repo;

    @Mock
    private BindingResult result;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ClinicController clinicController;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setEmail("test@example.com");
        doctor.setPass("password123");
    }

    @Test
    void testDoctorRegistration() {
        when(session.getAttribute(any(String.class))).thenReturn(null); // Mock behavior of session

        ModelAndView mav = clinicController.DoctorRegistration(session);

        assertEquals("DoctorRegistration", mav.getViewName());
        assertEquals(Doctor.class, mav.getModel().get("doctor").getClass());
    }

    @Test
    void testProcessSignupForm_HasErrors() {
        when(result.hasErrors()).thenReturn(true);
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("email", "Email is required"));
        when(result.getAllErrors()).thenReturn(errors);

        ModelAndView signupModel = new ModelAndView("DoctorRegistration.html");
        when(session.getAttribute(any(String.class))).thenReturn(null); // Mock behavior of session

        ModelAndView response = clinicController.processSignupForm(doctor, result, "password123", session);

        assertEquals("DoctorRegistration.html", response.getViewName());
        assertEquals(1, ((List<String>) response.getModel().get("errors")).size());
        verify(doctorRepository, never()).save(any(Doctor.class));
    }

    @Test
    void testProcessSignupForm_NoErrors() {
        when(result.hasErrors()).thenReturn(false);
        when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(null);

        ModelAndView response = clinicController.processSignupForm(doctor, result, "password123", session);

        assertEquals("redirect:/User/clinicHomepage", response.getViewName());
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }


    
}
