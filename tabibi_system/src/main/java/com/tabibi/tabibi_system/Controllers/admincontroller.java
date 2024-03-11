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
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.hibernate.usertype.UserType;
import org.mindrot.jbcrypt.BCrypt;
import com.tabibi.tabibi_system.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Admin")
public class admincontroller {

@Autowired
PatientRepository patientrepo;

@Autowired
DoctorRepository doctorrepo;

@Autowired
UserAccRepository userrepo;
@Autowired
UserTypeRepository user_type_repo;

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

   @GetMapping("addusers")
   public ModelAndView addusers() {
      ModelAndView mav = new ModelAndView("addusers.html");
      UserAcc user=new UserAcc();
      
      List<UserTypes>typeList = this.user_type_repo.findAll();
      System.out.println("--------------------------- the type list =  --------------------------------------");
for (UserTypes x : typeList) 
{
   System.out.println(x.getName());
   System.out.println(x.getUtid());

}


      mav.addObject("user", user);
      mav.addObject("types", typeList);
    //  mav.addObject("usertype", user_type_repo.findAll());

      return mav;
   }

   // @GetMapping("addusers")
   // public ModelAndView addusers() {
   //    ModelAndView mav = new ModelAndView("addusers.html");
   //    UserAcc user=new UserAcc();
   //    mav.addObject("user", user);
   //   mav.addObject("usertype", user_type_repo.findAll());
         
   //    return mav;
   
   // }
@PostMapping("addusers")
public String saveuser(@ModelAttribute UserAcc user) {
   String hash_password=BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12));
   user.setPass(hash_password);
   this.userrepo.save(user);
     
    return "added";
   
}



// @PostMapping("addusers")
// public String saveuser(@ModelAttribute UserAcc user) {
//    String hash_password=BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12));
//    user.setPass(hash_password);

//    this.userrepo.save(user);
     
//     return "added";
   
// }
@GetMapping("/patients")
public ModelAndView getpatientspage(){
   ModelAndView mav=new ModelAndView("patients.html");
   return mav;
}

}
