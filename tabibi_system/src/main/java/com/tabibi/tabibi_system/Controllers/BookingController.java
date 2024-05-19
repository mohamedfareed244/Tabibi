package com.tabibi.tabibi_system.Controllers;

import com.tabibi.tabibi_system.Models.Appointment;
import com.tabibi.tabibi_system.Models.Booking;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Repositories.AppointmentRepository;
import com.tabibi.tabibi_system.Repositories.BookingRepository;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PatientRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository ;
   
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("")
    public ModelAndView viewAppointmentsForBooking(@RequestParam(required = false) String specialization) {
        ModelAndView mav = new ModelAndView("booking");

        List<String> uniqueSpecializations = doctorRepository.findDistinctSpecializationsByAppointments();
        List<Appointment> appointmentList;

        if (specialization != null && !specialization.isEmpty()) {
            appointmentList = appointmentRepository.findByDoctorSpecialization(specialization);
            mav.addObject("selectedSpecialization", specialization);
        } else {
            appointmentList = appointmentRepository.findAll();
        }

        mav.addObject("appointmentList", appointmentList);
        mav.addObject("uniqueSpecializations", uniqueSpecializations);

        return mav;
    }
    @PostMapping("/bookAppointment")
    public RedirectView bookAppointment(@RequestParam long appointmentId ,HttpSession session)
    {
System.out.println("-------------------- BOOKING DETAILS");
Appointment selctedappointment = this.appointmentRepository.findByappId(appointmentId);
Object uidObject = session.getAttribute("uid");
long uid;
if (uidObject instanceof Integer) {
    uid = ((Integer) uidObject).longValue();
} else if (uidObject instanceof Long) {
    uid = (Long) uidObject;
} else {
    throw new IllegalStateException("Invalid user ID type");
}
Patient patient = this.patientRepository.findByUid(uid);
System.out.println("appointment id : " + appointmentId);
System.out.println("appointment  : " + selctedappointment.toString());
System.out.println("the uid : " + uid  + " the patient : " + patient.toString());
Booking bookedappointment = new Booking(selctedappointment,patient);
System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
System.out.println(bookedappointment.toString() + "booookedd");
this.bookingRepository.save(bookedappointment);


System.out.println();

return new RedirectView("/booking");


    }
}
