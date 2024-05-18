package com.tabibi.tabibi_system.Controllers;

import com.tabibi.tabibi_system.Models.Appointment;
import com.tabibi.tabibi_system.Repositories.AppointmentRepository;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

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
    @GetMapping("/bookAppointment")
    public void book(@RequestParam){

    }
}
