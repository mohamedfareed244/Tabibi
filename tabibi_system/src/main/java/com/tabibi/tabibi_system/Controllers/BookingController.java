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
    private PatientRepository patientRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("")
    public ModelAndView viewAppointmentsForBooking(@RequestParam(required = false) String specialization) {
        ModelAndView mav = new ModelAndView("booking");

        List<String> uniqueSpecializations = doctorRepository.findDistinctSpecializationsByAppointments();
        List<Appointment> appointmentList;

        if (specialization != null && !specialization.isEmpty()) {
            appointmentList = appointmentRepository.findByDoctorSpecializationAndStatus(specialization, "available");
        } else {
            appointmentList = appointmentRepository.findByStatus("available");
        }

        // Update the status of appointments with no available places
        appointmentList.forEach(appointment -> {
            if (appointment.getPlacesLeft() == 0) {
                appointment.setStatus("reserved");
                appointmentRepository.save(appointment);
            }
        });

        // Fetch the updated list of appointments
        if (specialization != null && !specialization.isEmpty()) {
            appointmentList = appointmentRepository.findByDoctorSpecializationAndStatus(specialization, "available");
        } else {
            appointmentList = appointmentRepository.findByStatus("available");
        }

        mav.addObject("appointmentList", appointmentList);
        mav.addObject("uniqueSpecializations", uniqueSpecializations);

        return mav;
    }

    @PostMapping("/bookAppointment")
    public RedirectView bookAppointment(@RequestParam long appointmentId, HttpSession session) {
        System.out.println("-------------------- BOOKING DETAILS");

        Appointment selectedAppointment = this.appointmentRepository.findByappId(appointmentId);

        if (selectedAppointment.getPlacesLeft() <= 0) {
            System.out.println("No available slots for this appointment");
            return new RedirectView("/booking?error=no_slots_available");
        }

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
        // System.out.println("appointment id : " + appointmentId);
        // System.out.println("appointment  : " + selectedAppointment.toString());
        // System.out.println("the uid : " + uid + " the patient : " + patient.toString());
        Booking bookedAppointment = new Booking(selectedAppointment, patient);
        // System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        // System.out.println(bookedAppointment.toString() + " booookedd");
        this.bookingRepository.save(bookedAppointment);
        selectedAppointment.setBooked(selectedAppointment.getBooked() + 1);
        this.appointmentRepository.save(selectedAppointment);
        // System.out.println("updated appointment : " + selectedAppointment);

        return new RedirectView("/booking");
    }
}
