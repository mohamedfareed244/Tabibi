package com.tabibi.tabibi_system.Controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RestController;

import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Pages;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserTypePages;
// import com.tabibi.tabibi_system.Models.UserTypePages;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Repositories.ClinicRepository;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Admin")
public class admincontroller {

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
   @Autowired
   ClinicRepository clinicRepository;

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
   public RedirectView savepage(@ModelAttribute Pages page) {
      this.pages_repo.save(page);

      return new RedirectView("/Admin/admin-dashboard");
   }

  
   @GetMapping("ClinicRegistration")
public ModelAndView ClinicRegistration() 
{
  ModelAndView mav = new ModelAndView("ClinicRegistration.html");
  Clinic clinic=new Clinic();
  mav.addObject("clinic", clinic);
  return mav;
}

public String hashpassword(String password)
{
   String encoddedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
   return encoddedPassword;
}

@PostMapping("ClinicRegistration")
public ModelAndView processSignupForm(@ModelAttribute ("clinic")  Clinic clinic, BindingResult result, @RequestParam("cpassword") String Confirm_pass) {
     ModelAndView SignupModel=new ModelAndView("ClinicRegistration.html");
     ModelAndView refresh=new ModelAndView("DoctorHomePage.html");


 List<String> errorMessages = new ArrayList<>();


 Clinic existingUser = clinicRepository.findByEmail(clinic.getEmail());
 if (existingUser != null) 
 {
errorMessages.add("Email already exists. Please choose a different email.");
 }

if (clinic.getPass().length() < 8) 
{
    errorMessages.add("Password must be at least 8 characters long.");
}


// if(!BCrypt.checkpw(Confirm_pass, doctor.getPass()))
// {
// errorMessages.add("Password and confirm password doesn't match");
// }
else
System.err.println("password match");

if (clinic.getPass().isEmpty())
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
    Clinic clinicc = clinic.getClinic();
    String encoddedPassword =hashpassword(clinic.getPass());
    clinicc.setPass(encoddedPassword);  
    clinicc.setUsertype(new UserTypes(2L));
    this.clinicRepository.save(clinicc);
}

    return refresh;
}

   @GetMapping("/addpermission")
   public ModelAndView addpermission(HttpSession session) {
      ModelAndView mav = new ModelAndView("addpermission.html");
      // UserTypes type=new UserTypes();
      // Pages page=new Pages();
      UserAcc user = new UserAcc();
      List<UserTypes> typelist = this.user_type_repo.findAll();

      List<Pages> pagelist = this.pages_repo.findAll();
      mav.addObject("user", user);

      mav.addObject("type", typelist);
      mav.addObject("page", pagelist);
      mav.addObject("usertypeID",session.getAttribute("usertypeID"));
      mav.addObject("usertype",session.getAttribute("usertype"));
      
      System.out.println("============================================ in get ");
      System.out.println(user);
      System.out.println(typelist);
      System.out.println(pagelist);
      
      return mav;
   }

   @PostMapping("/addpermission")
   public RedirectView savePermissions(@RequestParam("usertype")String usertype,@RequestParam("chosenpage")List<String> chosenpages ) {
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
   return new RedirectView("/Admin/admin-dashboard");
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
   public RedirectView saveuser(@ModelAttribute UserAcc user) {
      String hash_password = BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12));
      user.setPass(hash_password);
      this.userrepo.save(user);

      return new RedirectView("/Admin/admin-dashboard");

   }

   // @PostMapping("addusers")
   // public String saveuser(@ModelAttribute UserAcc user) {
   // String hash_password=BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12));
   // user.setPass(hash_password);

   // this.userrepo.save(user);

   // return "added";

   // }
  
   @GetMapping("/search")
   public ModelAndView getsearch() {
      ModelAndView mav = new ModelAndView("search_and_delete.html");
      return mav;
   }
   
   @GetMapping("/getdata")
   public String getData(@RequestParam  String name) {
       // Process the request with the "name" parameter
       return "Received parameter: " + name;
   }
   @GetMapping("/navigation")
   public ModelAndView getnavigation(HttpSession session) {
       ModelAndView mav = new ModelAndView("admin_navigation.html");
   
   
       Long type=(Long) session.getAttribute("usertypeID");
       
       System.out.println(type);

       List<UserTypePages> pagelist=this.page_type_repo.findByupid(type);
       List<String> pageNames = new ArrayList<>();
       List<String> pageLinks = new ArrayList<>();
       List<String> pageClasses = new ArrayList<>();
       List<String> pageIcons = new ArrayList<>();
   
       for(UserTypePages list:pagelist){
         Pages page = list.getPage();

      //   list.getPage().getName();
      //   list.getPage().getLinkaddress();
      //   list.getPage().getClassX();
      //   list.getPage().getIcons();
      pageNames.add(page.getName());
      pageLinks.add(page.getLinkaddress());
      pageClasses.add(page.getClassX());
      pageIcons.add(page.getIcons());

      //     Pages page=this.pages_repo.FindBypgid(list.getPage());
        }
       




       mav.addObject("usertypeID",session.getAttribute("usertypeID"));
       mav.addObject("usertype",session.getAttribute("usertype"));
       mav.addObject("pageNames", pageNames); // Add page names to the model
    mav.addObject("pageLinks", pageLinks); // Add page links to the model
    mav.addObject("pageClasses", pageClasses); // Add page classes to the model
    mav.addObject("pageIcons", pageIcons); // Add page icon
   
       return mav;
   }
}