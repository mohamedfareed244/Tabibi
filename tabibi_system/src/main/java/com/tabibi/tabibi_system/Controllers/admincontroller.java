package com.tabibi.tabibi_system.Controllers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/admin-dashboard")
   public ModelAndView getadmin_dashboard() {
    ModelAndView mav=new ModelAndView("admindashboard.html");
       return mav;
   }
   @GetMapping("/settings")
   public ModelAndView getaccount() {
    ModelAndView mav=new ModelAndView("Profile.html");
       return mav;
   }
   @GetMapping("/User")
   public String getpage() {
       return new String();
   }
   
}
