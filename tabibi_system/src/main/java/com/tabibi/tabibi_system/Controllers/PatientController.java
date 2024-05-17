package com.tabibi.tabibi_system.Controllers;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Repositories.PatientRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/patient")
public class PatientController {
        @Autowired
    private UserAccRepository UserAccRepository;
    @Autowired
    private PatientRepository patientRepository;

 @GetMapping("accountSettings")
     public ModelAndView getSettings(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("accountSettings.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        return mav;
     }
     @GetMapping("Profile")
     public ModelAndView getProfile(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("profile.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        mav.addObject("lastname",(String) session.getAttribute("lastname"));
        mav.addObject("number",(String) session.getAttribute("number"));
        return mav;
     }
     @GetMapping("EditProfile")
     public ModelAndView getEditProfile(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("editProfile.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        mav.addObject("lastname",(String) session.getAttribute("lastname"));
        mav.addObject("number",(String) session.getAttribute("number"));
        mav.addObject("uid",(Integer) session.getAttribute("uid"));

        return mav;
     }
     @PostMapping("/EditProfile")
     public RedirectView editprocess(HttpSession session,@RequestParam("email") String email,
     @RequestParam("firstname") String firstname,
     @RequestParam("lastname") String lastname,
     @RequestParam("number") String number) 
     {
         Integer uid = (Integer)session.getAttribute("uid");
         Patient PatientEdit = this.patientRepository.findByUid(uid);
         if (PatientEdit != null) {
             session.setAttribute("email", email);
             session.setAttribute("firstname", firstname);
             session.setAttribute("lastname", lastname);
             session.setAttribute("number", number);
             
             PatientEdit.setFirstname(firstname);
             PatientEdit.setLastname(lastname);
             PatientEdit.setNumber(number);
             PatientEdit.setEmail(email);
             this.patientRepository.save(PatientEdit);
             
             return new RedirectView("/User/patientHomepage");
         }
         return new RedirectView("/patient/EditProfile?error=incorrectPassword");
     
     }
     
     @GetMapping("/deleteAccount")
     public RedirectView deleteAccount(HttpSession session) {
         Integer uid = (Integer) session.getAttribute("uid");
         Patient PatientDelete = this.patientRepository.findByUid(uid);
         if (PatientDelete != null) {
                 this.patientRepository.delete(PatientDelete);
                 session.invalidate(); 
                 return new RedirectView("/User/login"); 
         }
         return new RedirectView("/patient/Profile"); 
     }

    
     @PostMapping("/edit")
     public RedirectView editpatient(@ModelAttribute Patient patient,HttpSession session){
        patient.setAge( (String)session.getAttribute("editAge"));
        patient.setAddress( (String)session.getAttribute("editAddress"));
        patient.setPid((Long) session.getAttribute("editPid"));
this.patientRepository.save(patient);
return new RedirectView("/patient/info?id="+(Long) session.getAttribute("editPid"));

     }
     

//      @PostMapping("/deletePatient")
// public RedirectView deletePatient(HttpSession session) {
//     Integer uid = (Integer) session.getAttribute("uid");
//     UserAcc userAcc = this.UserAccRepository.findByUid(uid);
//     if (userAcc != null) {
//         Patient patient = this.patientRepository.findByUserAcc(userAcc);
//         if (patient != null) {
//             this.patientRepository.delete(patient);
//             this.UserAccRepository.delete(userAcc);
//             session.invalidate(); 
//             return new RedirectView("/login");
//         }
//     }
//     return new RedirectView("/patient/Profile");
// }

    
}
