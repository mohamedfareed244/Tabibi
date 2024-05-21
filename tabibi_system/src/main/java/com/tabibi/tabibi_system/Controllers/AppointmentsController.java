package com.tabibi.tabibi_system.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.Appointment;
import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Repositories.AppointmentRepository;
import com.tabibi.tabibi_system.Repositories.ClinicRepository;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
 @Autowired
 UserTypePagesRepository page_type_repo;
 @Autowired
 UserTypeRepository user_type_repo;

@GetMapping("add")
public ModelAndView appointmentForm(HttpSession session) 
{
   ModelAndView mav= admincontroller.preparenavigation(session, "addAppointment.html", user_type_repo, page_type_repo);

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
public ModelAndView addAppointment(@Valid @ModelAttribute Appointment appointment  , BindingResult result,HttpSession session ) 

{
 ModelAndView addAppointment= admincontroller.preparenavigation(session, "addAppointment.html", user_type_repo, page_type_repo);

 ModelAndView home= admincontroller.preparenavigation(session, "ClinicHomePage.html", user_type_repo, page_type_repo);

List<String> errorMessages = new ArrayList<>();

  if (result.hasErrors()) 
  {
      System.err.println("fe error fe validations el appointments");
          for (ObjectError error : result.getAllErrors()) 
          {
          errorMessages.add(error.getDefaultMessage());
          System.out.println(errorMessages);
          }
           addAppointment.addObject("errors", errorMessages);
       
           List<Doctor> doctors = this.doctorRepository.findAll();
           List<Clinic> clinics = this.clinicRepository.findAll();
           addAppointment.addObject("doctors", doctors);
           addAppointment.addObject("clinics", clinics);
           addAppointment.addObject("appointment", appointment);
           return addAppointment;
  }
  else{
    this.appointmentRepository.save(appointment);
    return home;
  }

  
}
   


@GetMapping("view")
public ModelAndView viewAppointmentForm(HttpSession session){
 ModelAndView mav= admincontroller.preparenavigation(session, "viewappointments.html", user_type_repo, page_type_repo);

    List<Appointment> appointmentList = this.appointmentRepository.findAll();
    mav.addObject("appointmentList", appointmentList);
    
    return mav;
}

@GetMapping("edit/{appId}")
public ModelAndView editAppointmentForm(@PathVariable long appId,HttpSession session) 
{
ModelAndView mav= admincontroller.preparenavigation(session, "editAppointments.html", user_type_repo, page_type_repo);

Appointment oldApp=this.appointmentRepository.findByappId(appId);
System.out.println("-------------------------------------the appointment sent in the edit form :" + oldApp);
mav.addObject("oldApp", oldApp);
List<Doctor> doctors = this.doctorRepository.findAll();
List<Clinic> clinics = this.clinicRepository.findAll();
mav.addObject("doctors", doctors);
mav.addObject("clinics", clinics);
return mav;
}

    
    @PostMapping("edit/{appId}")
    public RedirectView updateAppointment(@ModelAttribute("oldApp") Appointment oldAppointment, @PathVariable Long appId) 
    {
    oldAppointment.setAppId(appId);
    this.appointmentRepository.save(oldAppointment);
        return new RedirectView("/appointments/view");
    }
   
     
   

@GetMapping("delete/{appId}")
@Transactional
public RedirectView deleteAppointment(@PathVariable long appId){
    this.appointmentRepository.deleteByappId(appId);
    return new RedirectView("/appointments/view");


}
}






