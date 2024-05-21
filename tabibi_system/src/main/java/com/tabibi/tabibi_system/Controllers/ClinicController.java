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
    @Autowired
    private ClinicRepository clinicRepository;
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
public ModelAndView processSignupForm(@Valid @ModelAttribute ("doctor")  Doctor doctor, BindingResult result, @RequestParam("cpassword") String Confirm_pass,HttpSession session) {
     ModelAndView SignupModel= admincontroller.preparenavigation(session, "DoctorRegistration.html", user_type_repo, page_type_repo);


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

@GetMapping("Profile")
public ModelAndView getProfile(HttpSession session)
{
ModelAndView mav= com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session, "ClinicProfile.html", user_type_repo, page_type_repo);
   
   mav.addObject("email",(String) session.getAttribute("email"));
   mav.addObject("firstname",(String) session.getAttribute("firstname"));
   mav.addObject("location",(String) session.getAttribute("Location"));
   mav.addObject("number",(String) session.getAttribute("number"));

   return mav;
}

@GetMapping("accountSettings")
public ModelAndView getSettings(HttpSession session)
{
 
ModelAndView mav= com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session, "ClinicAccountSettings.html", user_type_repo, page_type_repo);

   mav.addObject("email",(String) session.getAttribute("email"));
   mav.addObject("firstname",(String) session.getAttribute("firstname"));
   return mav;
}

@GetMapping("EditProfile")
public ModelAndView getEditProfile(HttpSession session)
{
ModelAndView mav= com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session, "EditClinicProfile.html", user_type_repo, page_type_repo);

   mav.addObject("email",(String) session.getAttribute("email"));
   mav.addObject("firstname",(String) session.getAttribute("firstname"));
   mav.addObject("location",(String) session.getAttribute("Location"));
   mav.addObject("number",(String) session.getAttribute("number"));
   mav.addObject("uid",(Integer) session.getAttribute("uid"));


   return mav;
}
@PostMapping("/EditProfile")
public RedirectView editprocess(HttpSession session,@RequestParam("email") String email,
@RequestParam("firstname") String firstname,
@RequestParam("location") String location,
@RequestParam("number") String number ) 
{
    Integer uid = (Integer)session.getAttribute("uid");
    Clinic clinicEdit = this.clinicRepository.findByUid(uid);
    if (clinicEdit != null) {
        session.setAttribute("email", email);
        session.setAttribute("firstname", firstname);
        session.setAttribute("Location", location);
        session.setAttribute("number", number);


        
 clinicEdit.setCname(firstname);
 clinicEdit.setEmail(email);
 clinicEdit.setCloc(location);
 clinicEdit.setCnumber(number);
        
        return new RedirectView("/User/clinicHomepage");
    }
    return new RedirectView("/Clinic/EditProfile?error=error");

}

@GetMapping("/deleteAccount")
public RedirectView deleteAccount(HttpSession session) {
    Integer uid = (Integer) session.getAttribute("uid");
    Clinic clinicDelete = this.clinicRepository.findByUid(uid);
    if (clinicDelete != null) {
            this.clinicRepository.delete(clinicDelete);
            session.invalidate(); 
            return new RedirectView("/User/Login"); 
    }
    return new RedirectView("/Clinic/Profile"); 
}
}

