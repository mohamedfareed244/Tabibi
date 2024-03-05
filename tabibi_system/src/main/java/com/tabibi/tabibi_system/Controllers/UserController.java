
package com.tabibi.tabibi_system.Controllers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import com.tabibi.tabibi_system.Repositories.UserRepository;
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



    @GetMapping("/Registration")

    public ModelAndView addUser()
    {
    ModelAndView mav=new ModelAndView("Registration.html");
    Patient newpatient=new Patient();
    UserAcc newUser=new UserAcc();
    mav.addObject("Patient", newpatient);
    mav.addObject("UserAcc", newUser);
      return mav;
    }

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute User user)
     {
      String encoddedPassword=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)) ;
      user.setPassword(encoddedPassword);
      this.UserRepository.save(user);
      return "ADDed ya basha to DataBAse";
     }


    @GetMapping("/AllUsers")
     public ModelAndView getUsers()
     {
         ModelAndView mav=new ModelAndView("search.html"); 
         List<User> users=this.UserRepository.findAll();
         mav.addObject("users", users);
         return mav;
     }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("name") String name, Model model) {
      List<User> users = UserRepository.findByName(name); 
       ModelAndView mag=new ModelAndView("searchResult.html");
        model.addAttribute("users", users);
      return mag;
    }

    
   
   @GetMapping("/navigation")
   public ModelAndView getnavigation() {
    ModelAndView mav=new ModelAndView("navigation.html");
       return mav;
   }
     
   @GetMapping("/account-settings")
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
