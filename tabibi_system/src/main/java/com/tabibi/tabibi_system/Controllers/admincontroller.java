package com.tabibi.tabibi_system.Controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PatientRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
import com.tabibi.tabibi_system.Repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.hibernate.usertype.UserType;
import org.mindrot.jbcrypt.BCrypt;
import com.tabibi.tabibi_system.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/User")
public class admincontroller {

@Autowired
PatientRepository patientrepo;

@Autowired
DoctorRepository doctorrepo;

@Autowired
UserAccRepository userrepo;

   @GetMapping("/admin-dashboard")
   public ModelAndView getadmin_dashboard() {
      ModelAndView mav = new ModelAndView("admindashboard.html");
      return mav;
   }

   @GetMapping("/addpage")
   public ModelAndView getpage() {

      ModelAndView mav = new ModelAndView("addpage.html");
      return mav;
   }

   @GetMapping("/settings")
   public ModelAndView account() {
      ModelAndView mav = new ModelAndView("Profile.html");
      return mav;
   }

   @GetMapping("/addusers")
   public ModelAndView addusers() {
      ModelAndView mav = new ModelAndView("addusers.html");
   User user=new User();
   mav.addObject("user", user);
      return mav;
   
   }
// @PostMapping("/addusers")
// public String saveuser(@ModelAttribute UserAcc user,@RequestParam String usertype) {
//    String hash_password=BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12));
//    user.setPass(hash_password);

//    userrepo.save(user);
//      if(usertype.equals("Doctor")){
// user.setUsertype(4);
// userrepo.save(user);
//      }else if(usertype.equals("Patient")){
//       user.setUser_type("Patient");
// userrepo.save(user);
//      }
    
//     return "added";
   
// }

}
