package com.tabibi.tabibi_system.Controllers;

import java.util.List;

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
     @RequestParam("number") String number) {
         Integer uid = (Integer)session.getAttribute("uid");
         UserAcc newUser = this.UserAccRepository.findByUid(uid);
         if (newUser != null) {
             session.setAttribute("email", email);
             session.setAttribute("firstname", firstname);
             session.setAttribute("lastname", lastname);
             session.setAttribute("number", number);
             Patient patient = this.patientRepository.findByUserAcc(newUser);
             patient.setFirstname(firstname);
             patient.setLastname(lastname);
             patient.setNumber(number);
             newUser.setEmail(email);
             this.UserAccRepository.save(newUser);
             this.patientRepository.save(patient);
             
             return new RedirectView("/User/patientHomepage");
         }
         return new RedirectView("/patient/EditProfile?error=incorrectPassword");
     
     }
     
     @GetMapping("/deleteAccount")
     public RedirectView deleteAccount(HttpSession session) {
         Integer uid = (Integer) session.getAttribute("uid");
         UserAcc userAcc = this.UserAccRepository.findByUid(uid);
         if (userAcc != null) {
             Patient patient = this.patientRepository.findByUserAcc(userAcc);
             if (patient != null) {
                 this.patientRepository.delete(patient);
                this.UserAccRepository.delete(userAcc);
                 session.invalidate(); 
                 return new RedirectView("/User/login"); 
             }
         }
         return new RedirectView("/patient/Profile"); 
     }

     @GetMapping("/getdata")
     public String getData(@RequestParam  String name) {
        List<Patient> mylist=this.patientRepository.findByfirstname(name);
        System.out.println(mylist+name);
        if(mylist.size()==0){
           return "no patients fonded ";
        }else{
           String data="";
           for(int i=0;i<mylist.size();i++){
              Patient patient=mylist.get(i);
         data+="<tr>";
         data+="<td>";
  data+=(patient.getPid());
  data+="</td>";
  data+="<td>";
  data+=(patient.getFirstname());
  data+="</td>";
  data+="<td>";
  data+=(patient.getLastname());
  data+="</td>";
  data+="<td>";
  data+=(patient.getNumber());
  data+="</td> </tr>";
  
           }
           return data;
        }
      
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
