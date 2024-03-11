
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
import com.tabibi.tabibi_system.Repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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
    Patient patient=new Patient();
    UserAcc userAcc=new UserAcc();
    userAcc.setUid(1);
    patient.setUserAcc(userAcc);
    mav.addObject("patient", patient);
      return mav;
    }



    @PostMapping("/signup")
    public String saveUser(@ModelAttribute Patient patient)
     {
    System.err.println("bada2 ysave");
     UserAcc currUser=patient.getUserAcc();
     String encoddedPassword=BCrypt.hashpw(currUser.getPass(), BCrypt.gensalt(12)) ;
      currUser.setPass(encoddedPassword);
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
     public RedirectView loginprocess(@RequestParam("email")String email,@RequestParam("pass")String pass, HttpSession session)
      {
         System.out.println("post mapping");

        UserAcc newUser=this.UserAccRepository.findByEmail(email);
        Boolean PasswordsMatch=BCrypt.checkpw(pass, newUser.getPass());
        if(PasswordsMatch)
        {
            session.setAttribute("email", newUser.getEmail());
            return new RedirectView("/User/accountSettings");
        }
        else
        {
            return new RedirectView("/Login");
        }
     }

     


    // @GetMapping("/search")
    // public ModelAndView search(@RequestParam("name") String name, Model model) {
    //   List<User> users = UserRepository.findByName(name); 
    //    ModelAndView mag=new ModelAndView("searchResult.html");
    //     model.addAttribute("users", users);
    //   return mag;
    // }

    
   
   @GetMapping("/navigation")
   public ModelAndView getnavigation() {
    ModelAndView mav=new ModelAndView("navigation.html");
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
