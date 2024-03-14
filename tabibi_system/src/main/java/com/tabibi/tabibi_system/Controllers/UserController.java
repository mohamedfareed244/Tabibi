
package com.tabibi.tabibi_system.Controllers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tabibi.tabibi_system.Repositories.*;
import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Models.sup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired 
    private DoctorRepository doctorRepository;



// @GetMapping("/signup")
// public ModelAndView showSignupForm() {
//     ModelAndView mav = new ModelAndView("signup.html");
//     sup signupForm = new sup();
//     signupForm.setUser(new UserAcc());
//     signupForm.setPatient(new Patient());
//     signupForm.setDoctor(new Doctor());
//     signupForm.setClinic(new Clinic());
//     mav.addObject("signupForm", signupForm);
//     return mav;
// }

@GetMapping("")
public ModelAndView getlanding() {
    ModelAndView mav = new ModelAndView("LandingPage.html");
    return mav;
}

@GetMapping("/signup")
public ModelAndView showSignupForm() {
    ModelAndView mav = new ModelAndView("signup.html");
    sup signupForm = new sup();
    signupForm.setUser(new UserAcc());
    signupForm.setPatient(new Patient());
    signupForm.setDoctor(new Doctor());
    signupForm.setClinic(new Clinic());
    mav.addObject("signupForm", signupForm);
    return mav;
}

@PostMapping("/signup")
public RedirectView processSignupForm(@Valid sup sign_vali, BindingResult result, @ModelAttribute("signupForm")sup signupForm, @RequestParam("userType") String userType , @RequestParam("cpassword") String Confirm_pass) {
    UserAcc userAcc = signupForm.getUser();
    sign_vali=signupForm;
    String encoddedPassword = BCrypt.hashpw(userAcc.getPass(), BCrypt.gensalt(12));
    userAcc.setPass(encoddedPassword); 
    switch (userType)
    {
        case "patient":
if (result.hasErrors()) 
{
    System.err.println("Stop error fel vali");
    return new RedirectView("/User/Login");
}
else
{
            userAcc.setUsertype(new UserTypes(4L));
            Patient patient = signupForm.getPatient();
            patient.setUserAcc(userAcc);
            this.UserAccRepository.save(userAcc);
            this.patientRepository.save(patient);
            break;
}
        case "doctor":
        if (result.hasErrors()) 
{
    System.err.println("Stop error fel vali");
    return new RedirectView("/User/Login");
}
else
{
          userAcc.setUsertype(new UserTypes(3L));
          Doctor doctor = signupForm.getDoctor();
          doctor.setUserAcc(userAcc);
           this.UserAccRepository.save(userAcc);
          this.doctorRepository.save(doctor);
          break;
}
        case "clinic":
        if (result.hasErrors()) 
{
    System.err.println("Stop error fel vali");
    return new RedirectView("/User/Login");
}
else
{
        userAcc.setUsertype(new UserTypes(2L));
        Clinic clinic=signupForm.getClinic();
        clinic.setUserAcc(userAcc);
        this.UserAccRepository.save(userAcc);
        this.clinicRepository.save(clinic);
            break;
}
        default:
            // Default case
            break;
    }

    return new RedirectView("/User/Login");
}




    // @PostMapping("/signup")
    // public String saveUser(@ModelAttribute("patient") Patient patient)
    //  {
    // System.err.println("bada2 ysave");
    // UserAcc currAcc=new UserAcc();
    // currAcc=patient.getUserAcc();
    //  String encoddedPassword=BCrypt.hashpw(currAcc.getPass(), BCrypt.gensalt(12)) ;
    //   currAcc.setPass(encoddedPassword);
    //   currAcc.setImage("testimage");
    //   currAcc.setUid(1);
    //   System.err.println("password coded ");
    //   this.patientRepository.save(patient);
    //   return "Added ya basha to DataBase";
    //  }


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
     
         UserAcc newUser = this.UserAccRepository.findByEmail(email);
         if (newUser != null) {
             Boolean PasswordsMatch = BCrypt.checkpw(pass, newUser.getPass());
             if (PasswordsMatch) {
                 if (newUser.getUsertype().getUtid() == 4) 
                 {
                    Patient patient = this.patientRepository.findByUserAcc(newUser);
                     session.setAttribute("email", newUser.getEmail());
                     session.setAttribute("firstname", patient.getFirstname());
                     session.setAttribute("number", patient.getNumber());
                     session.setAttribute("lastname", patient.getLastname());
                     session.setAttribute("uid", newUser.getUid());
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
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
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

     

   @GetMapping("/footer")
   public ModelAndView getfooter() {
    ModelAndView mav=new ModelAndView("footer.html");
       return mav;
   }
}
