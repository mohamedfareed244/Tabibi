package com.tabibi.tabibi_system.Controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RestController;

import com.tabibi.tabibi_system.Models.Admin;
import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Pages;
import com.tabibi.tabibi_system.Models.Patient;
// import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserTypePages;
// import com.tabibi.tabibi_system.Models.UserTypePages;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Repositories.AdminRepository;
import com.tabibi.tabibi_system.Repositories.ClinicRepository;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PagesRepository;
import com.tabibi.tabibi_system.Repositories.PatientRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
// import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.usertype.UserType;
import org.mindrot.jbcrypt.BCrypt;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
   public UserTypePagesRepository page_type_repo;
   @Autowired
   ClinicRepository clinicRepository;

   @Autowired
   AdminRepository adminRepository;
@Autowired 
PatientRepository patientRepository;
   // @Autowired
   // UserTypePagesRepository page_type_repo;
   @GetMapping("/admin-dashboard")
   public ModelAndView getadmin_dashboard(HttpSession session) {
      ModelAndView mav = preparenavigation(session,"admindashboard.html",this.user_type_repo,this.page_type_repo);
      return mav;
   }

   @GetMapping("/addpage")
   public ModelAndView getpage(HttpSession session) {

      ModelAndView mav = preparenavigation(session,"addpage.html",this.user_type_repo,this.page_type_repo);
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
public ModelAndView ClinicRegistration(HttpSession session) 
{
  ModelAndView mav = preparenavigation(session,"ClinicRegistration.html",this.user_type_repo,this.page_type_repo);

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
return new ModelAndView("redirect:/Admin/admin-dashboard");

}


}

   @GetMapping("/addpermission")
   public ModelAndView addpermission(HttpSession session) {
      ModelAndView mav = preparenavigation(session,"addpermission.html",this.user_type_repo,this.page_type_repo);
   
      UserAcc user = new UserAcc();
      List<UserTypes> typelist = this.user_type_repo.findAll();

      List<Pages> pagelist = this.pages_repo.findAll();
      mav.addObject("user", user);

      mav.addObject("type", typelist);
      mav.addObject("page", pagelist);
      mav.addObject("usertypeID",session.getAttribute("usertypeID"));
      mav.addObject("usertype",session.getAttribute("usertype"));
      
      
      return mav;
   }

   @PostMapping("/addpermission")
   @Transactional
   public RedirectView savePermissions(@RequestParam("usertype")Long usertype,@RequestParam("chosenpage")List<Long> chosenpages ) {
      System.out.println(usertype);
       UserTypes type=this.user_type_repo.findByutid(usertype);
       System.out.println(type.getName());
       List<UserTypePages> alltypes=this.page_type_repo.findByUsertype(type);
       System.out.println(usertype);
for (int i=0;i<alltypes.size();i++){
   this.page_type_repo.deleteByUsertype((alltypes.get(i).getUsertype()));
}

      for (Long pagename : chosenpages) {
         Pages p=this.pages_repo.findBypgid(pagename);
         
         UserTypePages utp = new UserTypePages();


         utp.setPage(p);
         utp.setUsertype(type);
         

      this.page_type_repo.save(utp);
      }
   return new RedirectView("/Admin/navigation");
}

   @GetMapping("/settings")
   public ModelAndView account() {
      ModelAndView mav = new ModelAndView("profile.html");
      return mav;
   }

   @GetMapping("/addadmin")
   public ModelAndView addusers(HttpSession session) {
      ModelAndView mav = preparenavigation(session,"addadmin.html",this.user_type_repo,this.page_type_repo);

      Admin user = new Admin();
     // List<UserTypes> typeList = this.user_type_repo.findAll();
     
      mav.addObject("user", user);
      //mav.addObject("types", typeList);
      return mav;
   }

   @PostMapping("addadmin")
   public RedirectView saveuser(@ModelAttribute Admin admin) {
      String hash_password = BCrypt.hashpw(admin.getPass(), BCrypt.gensalt(12));
      admin.setPass(hash_password);
      admin.setUsertype(new UserTypes(1));
      this.adminRepository.save(admin);
      return new RedirectView("/Admin/admin-dashboard");

   }

  
   @GetMapping("/getsearchdata")
   public String getsearchData(@RequestParam String name, @RequestParam String type) {
       String data = "";
   
       if (type.equals("patient")) {
           List<Patient> mylist = this.patientRepository.findAllByEmail(name);
           data += "<tr><th>Id</th><th>Email</th><th>First Name</th><th>Last Name</th><th>Number</th></tr>";
           if (mylist.size() == 0) {
               data += "<tr><td colspan='5'>No patients found</td></tr>";
           } else {
               for (Patient patient : mylist) {
                   data += "<tr onclick='edit(" + patient.getUid() + ")'>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + patient.getUid() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + patient.getEmail() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + patient.getFirstname() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + patient.getLastname() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + patient.getNumber() + "</td>";
                   data += "</tr>";
               }
           }
       } else if (type.equals("clinic")) {
           List<Clinic> mylist = this.clinicRepository.findAllByEmail(name);
           data += "<tr><th>Id</th><th>Name</th><th>Location</th><th>Contact</th></tr>";
           if (mylist.size() == 0) {
               data += "<tr><td colspan='4'>No clinics found</td></tr>";
           } else {
               for (Clinic clinic : mylist) {
                   data += "<tr onclick='edit(" + clinic.getUid() + ")'>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + clinic.getUid() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + clinic.getCname() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + clinic.getCloc() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + clinic.getCnumber() + "</td>";
                   data += "</tr>";
               }
           }
       } else if (type.equals("doctor")) {
           List<Doctor> mylist = this.doctorrepo.findAllByEmail(name);
           data += "<tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Specialization</th></tr>";
           if (mylist.size() == 0) {
               data += "<tr><td colspan='4'>No doctors found</td></tr>";
           } else {
               for (Doctor doctor : mylist) {
                   data += "<tr onclick='edit(" + doctor.getUid() + ")'>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + doctor.getUid() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + doctor.getFirstname() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + doctor.getLastname() + "</td>";
                   data += "<td style='border: 1px solid #ddd; padding: 8px;'>" + doctor.getSpecialization() + "</td>";
                   data += "</tr>";
               }
           }
       } else {
           return "<tr><td colspan='5'>Invalid type</td></tr>";
       }
   
       return data;
   }
   
   
   @GetMapping("/search")
   public ModelAndView getSearchPage(HttpSession session) {
      ModelAndView mav = preparenavigation(session,"search_and_delete",this.user_type_repo,this.page_type_repo);
       
       return mav;
   }
   

   @GetMapping("/getdata")
   public String getData(@RequestParam  String name) {
       
       return "Received parameter: " + name;
   }
   @GetMapping("/admin_navigation")
   public ModelAndView getstaticnavigation(HttpSession session) {

      ModelAndView mav = preparenavigation(session,"admin_navigation.html",this.user_type_repo,this.page_type_repo);

      return mav;
   }  
   public static ModelAndView preparenavigation(HttpSession session, String viewName,UserTypeRepository userTypeRepo, UserTypePagesRepository pageTypeRepo) {
      ModelAndView mav = new ModelAndView(viewName);
   
   
       Long type=(Long) session.getAttribute("usertypeID");
       UserTypes userType = userTypeRepo.findByutid(type);

       List<UserTypePages> pagelist=pageTypeRepo.findByUsertype(userType);

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
       mav.addObject("pageNames", pageNames); 
       mav.addObject("pageLinks", pageLinks); 
    mav.addObject("pageClasses", pageClasses); 
    mav.addObject("pageIcons", pageIcons); 
   
       return mav;
   }


   @GetMapping("/navigation")
   public ModelAndView getNavigation(HttpSession session) {
       return preparenavigation(session, "navigation.html",this.user_type_repo, this.page_type_repo);
   }

   @GetMapping("/userlogs")
   public ModelAndView GetUserLogPage() {
ModelAndView mav =new ModelAndView("userlog.html");
return mav;

   }


}