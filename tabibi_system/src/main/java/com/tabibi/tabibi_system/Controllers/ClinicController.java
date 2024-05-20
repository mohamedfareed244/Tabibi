package com.tabibi.tabibi_system.Controllers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tabibi.tabibi_system.Repositories.*;
import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.SignupWrapper;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserTypePages;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Models.SignupWrapper;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Objects;


@RestController
@RequestMapping("/Clinic")
public class ClinicController {
    @Autowired
    UserTypeRepository user_type_repo;
    @Autowired
    public UserTypePagesRepository page_type_repo;
    @Autowired
    PagesRepository pages_repo;
    @Autowired
    private DoctorRepository doctorRepository;
    @GetMapping("DoctorRegistration")
public ModelAndView DoctorRegistration(HttpSession session) 
{
    ModelAndView mav= admincontroller.preparenavigation(session, "DoctorRegistration", user_type_repo, page_type_repo);
  // ModelAndView mav = new ModelAndView("DoctorRegistration.html");
   Doctor doctor=new Doctor();
   mav.addObject("doctor", doctor);
   return mav;
}

public String hashpassword(String password)
{
    String encoddedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
    return encoddedPassword;
}


@PostMapping("DoctorRegistration")
public ModelAndView processSignupForm(@ModelAttribute ("doctor")  Doctor doctor, BindingResult result, @RequestParam("cpassword") String Confirm_pass) {
     ModelAndView SignupModel=new ModelAndView("DoctorRegistration.html");


 List<String> errorMessages = new ArrayList<>();


 Doctor existingUser = doctorRepository.findByEmail(doctor.getEmail());
 if (existingUser != null) 
 {
errorMessages.add("Email already exists. Please choose a different email.");
 }

if (doctor.getPass().length() < 8) 
{
    errorMessages.add("Password must be at least 8 characters long.");
}


// if(!BCrypt.checkpw(Confirm_pass, doctor.getPass()))
// {
// errorMessages.add("Password and confirm password doesn't match");
// }
else
System.err.println("password match");

if (doctor.getPass().isEmpty())
 {
    errorMessages.add("Password is required");
}

if (result.hasErrors()) 
{
    for (ObjectError error : result.getAllErrors()) 
    {
    errorMessages.add(error.getDefaultMessage());
    }
     SignupModel.addObject("errors", errorMessages);
     return SignupModel;
}
else
{
    Doctor doctorr = doctor.getDoctor();
    String encoddedPassword =hashpassword(doctor.getPass());
    doctorr.setPass(encoddedPassword);  
    doctorr.setUsertype(new UserTypes(3L));
    this.doctorRepository.save(doctorr);
}

return new ModelAndView("redirect:/User/clinicHomepage");
}
}

