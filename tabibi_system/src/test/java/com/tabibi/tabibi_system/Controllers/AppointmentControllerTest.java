package com.tabibi.tabibi_system.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import com.tabibi.tabibi_system.Models.Appointment;
import com.tabibi.tabibi_system.Repositories.AppointmentRepository;
import com.tabibi.tabibi_system.Repositories.BookingRepository;
import com.tabibi.tabibi_system.Repositories.ClinicRepository;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PatientRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock 
    private DoctorRepository doctorRepository;

    @Mock
    private ClinicRepository clinicRepository;

    @Mock
    UserTypePagesRepository page_type_repo;

    @Mock
    UserTypeRepository user_type_repo;

    @Mock
    UserAccRepository UserAccRepository;

    @Mock
    PatientRepository patientRepository;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    private BindingResult result;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AppointmentsController appointmentController;

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment();
        appointment.setStatus("Scheduled");
        appointment.setTime("2:00 pm");
        appointment.setPrice("100");
        appointment.setCapacity(10);
        appointment.setBooked(5);
    }

    @SuppressWarnings("unchecked")
    @Test
    void addAppointment_HasErrors() {
        when(result.hasErrors()).thenReturn(true);
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("status", "Status is required"));
        when(result.getAllErrors()).thenReturn(errors);

        ModelAndView addAppointmentMav = new ModelAndView("addAppointment.html");
        when(session.getAttribute(any(String.class))).thenReturn(null); // Mock behavior of session

        ModelAndView response = appointmentController.addAppointment(appointment, result, session);

        assertEquals("addAppointment.html", response.getViewName());
        assertEquals(1, ((List<String>) response.getModel().get("errors")).size());
        verify(appointmentRepository, never()).save(any(Appointment.class));
    }

    @Test
    void addAppointment_NoErrors() {
        when(result.hasErrors()).thenReturn(false);
        ModelAndView homeMav = new ModelAndView("ClinicHomePage.html");
        when(session.getAttribute(any(String.class))).thenReturn(null); // Mock behavior of session

        ModelAndView response = appointmentController.addAppointment(appointment, result, session);

        assertEquals("ClinicHomePage.html", response.getViewName());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }
}
