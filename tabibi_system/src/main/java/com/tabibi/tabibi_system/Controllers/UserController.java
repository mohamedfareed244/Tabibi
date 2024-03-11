
package com.tabibi.tabibi_system.Controllers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RestController;
import com.tabibi.tabibi_system.Repositories.*;
import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private UserAccRepository UserAccRepository;
    @Autowired
    private PatientRepository patientRepository;




    @GetMapping("/Registration")
    public ModelAndView addUser()
    {
    ModelAndView mav=new ModelAndView("Registration.html");
    System.out.println("dakhal controller");
    //models
    Patient patient=new Patient();
    Doctor doctor=new Doctor();
    Clinic clinic=new Clinic();
//linking models to useracc
    UserAcc userAccpatient=new UserAcc();
    UserAcc userAccdoc=new UserAcc();
    UserAcc userAccClinic=new UserAcc();

    //adjusting the userID
    userAccpatient.setUid(1);
    patient.setUserAcc(userAccpatient);

    userAccdoc.setUid(2);
    doctor.setUserAcc(userAccdoc);

    userAccClinic.setUid(3);
    clinic.setUserAcc(userAccClinic);

    mav.addObject("patient", patient);
    mav.addObject("doctor", doctor);
    mav.addObject("Clinic", clinic);
      return mav;
    }


    @PostMapping("/signupClinic")
    public String saveUser(@ModelAttribute("Clinic") Clinic clinic)
     {
        System.err.println("bada2 ysave");
        UserAcc currAcc=new UserAcc();
        currAcc=clinic.getUserAcc();
        String encoddedPassword=BCrypt.hashpw(currAcc.getPass(), BCrypt.gensalt(12)) ;
        currAcc.setPass(encoddedPassword);
        currAcc.setImage("testimage");
        currAcc.setUid(2);
        System.err.println("password coded ");    
       this.UserAccRepository.save(currAcc);
       this.clinicRepository.save(clinic);
       return "Added ya basha to DataBase";
     }

    @PostMapping("/signupdoc")
    public String saveUser(@ModelAttribute("doctor") Doctor doctor)
     {
        System.err.println("bada2 ysave");
        UserAcc currAcc=new UserAcc();
        currAcc=doctor.getUserAcc();
        String encoddedPassword=BCrypt.hashpw(currAcc.getPass(), BCrypt.gensalt(12)) ;
        currAcc.setPass(encoddedPassword);
        currAcc.setImage("testimage");
        currAcc.setUid(2);
        System.err.println("password coded ");    
       this.UserAccRepository.save(currAcc);
       this.doctorRepository.save(doctor);
       return "Added ya basha to DataBase";
     }
    @PostMapping("/signup")
    public String saveUser(@ModelAttribute("patient") Patient patient)
     {
    System.err.println("bada2 ysave");
    UserAcc currAcc=new UserAcc();
     currAcc=patient.getUserAcc();
     String encoddedPassword=BCrypt.hashpw(currAcc.getPass(), BCrypt.gensalt(12)) ;
      currAcc.setPass(encoddedPassword);
      currAcc.setImage("testimage");
      currAcc.setUid(1);
      System.err.println("password coded ");
      this.patientRepository.save(patient);
      return "Added ya basha to DataBase";
     }


    @GetMapping("/AllUsers")
     public ModelAndView getUsers()
     {
         ModelAndView mav=new ModelAndView("search.html"); 
         List<User> users=this.UserRepository.findAll();
         mav.addObject("users", users);
         return mav;
     }

     @GetMapping("/Login")
     public ModelAndView Login()
     {
         ModelAndView mav=new ModelAndView("Login.html"); 
         UserAcc user=new UserAcc();
         mav.addObject("user", user);
         return mav;
     }
     @PostMapping("/Login")
     public RedirectView loginprocess(@RequestParam("email") String email, @RequestParam("pass") String pass, HttpSession session) {
         System.out.println("post mapping");
     
         UserAcc newUser = this.UserAccRepository.findByEmail(email);
         if (newUser != null) {
             Boolean PasswordsMatch = BCrypt.checkpw(pass, newUser.getPass());
             if (PasswordsMatch) {
                 if (newUser.getUsertype().getUtid() == 4) 
                 {
                    Patient patient = this.PatientRepository.findByUserAcc(newUser);
                     session.setAttribute("email", newUser.getEmail());
                     session.setAttribute("name", patient.getFirstname());
                     System.out.println(session.getAttribute("email"));
                     return new RedirectView("/User/patientHomepage");
                 } else {
                     return new RedirectView("/User/Login?error=incorrectPassword");
                 }
             } else {
                 return new RedirectView("/User/Login?error=incorrectPassword");
             }
         } else {
             return new RedirectView("/User/Login?error=userNotFound");
         }
     }
     

     @GetMapping("patientHomepage")
     public ModelAndView GetIndex(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("patientHomepage.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("name",(String) session.getAttribute("name"));
        return mav;
     }
    
     @GetMapping("patients")
     public ModelAndView Getpatients()
     {
        ModelAndView mav=new ModelAndView("patients.html");
        return mav;
     }
         
     
     

     


    // @GetMapping("/search")
    // public ModelAndView search(@RequestParam("name") String name, Model model) {
    //   List<User> users = UserRepository.findByName(name); 
    //    ModelAndView mag=new ModelAndView("searchResult.html");
    //     model.addAttribute("users", users);
    //   return mag;
    // }

    
   
   @GetMapping("/navigation")
public ModelAndView getnavigation(HttpSession session) {
    ModelAndView mav = new ModelAndView("navigation.html");
    mav.addObject("email", (String) session.getAttribute("email"));
    return mav;
}

     
   @GetMapping("accountSettings")
   public ModelAndView getaccount_settings() {
    ModelAndView mav=new ModelAndView("AccountSettings.html");
       return mav;
   }
   @GetMapping("/footer")
   public ModelAndView getfooter() {
    ModelAndView mav=new ModelAndView("footer.html");
       return mav;
   }
}
