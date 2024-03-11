package com.tabibi.tabibi_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tabibi.tabibi_system.Models.Appointment;
import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Repositories.AppointmentRepository;
import com.tabibi.tabibi_system.Repositories.ClinicRepository;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("appointments")
public class AppointmentsController 
{
 @Autowired
 private AppointmentRepository appointmentRepository;
 @Autowired 
 private DoctorRepository doctorRepository;
 @Autowired
 private ClinicRepository clinicRepository;

@GetMapping("add")
public ModelAndView appointmentForm() 
{
   ModelAndView mav = new ModelAndView("AddAppointment.html");
   mav.addObject("appointment", new Appointment());
   List<Doctor> doctors = this.doctorRepository.findAll();
   List<Clinic> clinics = this.clinicRepository.findAll();
   mav.addObject("doctors", doctors);
   mav.addObject("clinics", clinics);
   System.out.println("---------------------------------------------------------------------------------");
   System.out.println("list of clinics" + clinics);
   System.out.println("list of docs" + doctors);
   return mav;
}

@PostMapping("add")
public String addAppointment(@ModelAttribute Appointment appointment ) 
{

    this.appointmentRepository.save(appointment);
    return "added to db";
}


}
