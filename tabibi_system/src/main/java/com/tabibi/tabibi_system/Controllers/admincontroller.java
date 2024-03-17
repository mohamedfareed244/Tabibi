package com.tabibi.tabibi_system.Controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Pages;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserTypePages;
// import com.tabibi.tabibi_system.Models.UserTypePages;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PagesRepository;
import com.tabibi.tabibi_system.Repositories.PatientRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
import com.tabibi.tabibi_system.Repositories.UserRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
// import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

   @Autowired
   PagesRepository pages_repo;
   @Autowired
   UserTypePagesRepository page_type_repo;

   // @Autowired
   // UserTypePagesRepository page_type_repo;
   @GetMapping("/admin-dashboard")
   public ModelAndView getadmin_dashboard() {
      ModelAndView mav = new ModelAndView("admindashboard.html");
      return mav;
   }

   @GetMapping("/addpage")
   public ModelAndView getpage() {

      ModelAndView mav = new ModelAndView("addpage.html");
      Pages page = new Pages();
      mav.addObject("page", page);

      return mav;
   }

   @PostMapping("/addpage")
   public String savepage(@ModelAttribute Pages page) {
      this.pages_repo.save(page);

      return "added";
   }

   @GetMapping("/addpermission")
   public ModelAndView addpermission() {
      ModelAndView mav = new ModelAndView("addpermission.html");
      // UserTypes type=new UserTypes();
      // Pages page=new Pages();
      UserAcc user = new UserAcc();
      List<UserTypes> typelist = this.user_type_repo.findAll();

      List<Pages> pagelist = this.pages_repo.findAll();
      mav.addObject("user", user);

      mav.addObject("type", typelist);
      mav.addObject("page", pagelist);
      System.out.println("============================================ in get ");
      System.out.println(user);
      System.out.println(typelist);
      System.out.println(pagelist);
      return mav;
   }

   @PostMapping("/addpermission")
   public String savePermissions(@RequestParam("usertype")String usertype,@RequestParam("chosenpage")List<String> chosenpages ) {
      UserTypes type=this.user_type_repo.findByname(usertype);
      for (String pagename : chosenpages) {
      Pages p=this.pages_repo.findByname(pagename);

      UserTypePages utp = new UserTypePages();
utp.setPage(p);
utp.setUsertype(type);
      System.out.println("00000000000000000000000000000000e88392010============================");
      System.out.println(utp);
      System.out.println(usertype);

      this.page_type_repo.save(utp);
   }
   return "granted";
}

   @GetMapping("/settings")
   public ModelAndView account() {
      ModelAndView mav = new ModelAndView("profile.html");
      return mav;
   }

   @GetMapping("/addusers")
   public ModelAndView addusers() {
      ModelAndView mav = new ModelAndView("addusers.html");
      UserAcc user = new UserAcc();
      List<UserTypes> typeList = this.user_type_repo.findAll();
      System.out.println("--------------------------- the type list =  --------------------------------------");
      for (UserTypes x : typeList) {
         System.out.println(x.getName());
         System.out.println(x.getUtid());
      }
      mav.addObject("user", user);
      mav.addObject("types", typeList);
      return mav;
   }

   @PostMapping("addusers")
   public String saveuser(@ModelAttribute UserAcc user) {
      String hash_password = BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12));
      user.setPass(hash_password);
      this.userrepo.save(user);

      return "added";

   }

   // @PostMapping("addusers")
   // public String saveuser(@ModelAttribute UserAcc user) {
   // String hash_password=BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12));
   // user.setPass(hash_password);

   // this.userrepo.save(user);

   // return "added";

   // }
   @GetMapping("/patients")
   public ModelAndView getpatientspage() {
      ModelAndView mav = new ModelAndView("patients.html");
      return mav;
   }
   @GetMapping("/navigation")
   public ModelAndView getnavigation(HttpSession session) {
       ModelAndView mav = new ModelAndView("navigation.html");
   
   
       Long type=(Long) session.getAttribute("usertypeID");
      
       List<UserTypePages> pagelist=this.page_type_repo.findByupid(type);
       mav.addObject("usertypeID",type);
       
       mav.addObject("navtype", pagelist);
   
       return mav;
   }
   @GetMapping("/getdata")
   public String getData(@RequestParam  String name) {
       // Process the request with the "name" parameter
       return "Received parameter: " + name;
   }
       
}