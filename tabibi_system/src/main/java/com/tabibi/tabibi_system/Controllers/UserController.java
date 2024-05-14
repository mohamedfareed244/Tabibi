
package com.tabibi.tabibi_system.Controllers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tabibi.tabibi_system.Repositories.*;
import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.SignupWrapper;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserTypePages;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Models.SignupWrapper;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Objects;



@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserRepository UserRepository;
    @Autowired
     UserAccRepository UserAccRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired 
    private DoctorRepository doctorRepository;
    @Autowired
    UserTypePagesRepository page_type_repo;
 

    public UserController() {
    }

    public UserController(UserAccRepository UserAccRepository) {
        this.UserAccRepository = UserAccRepository;
    }

    public UserAccRepository getUserAccRepository() {
        return this.UserAccRepository;
    }

    public void setUserAccRepository(UserAccRepository UserAccRepository) {
        this.UserAccRepository = UserAccRepository;
    }

    public UserController UserAccRepository(UserAccRepository UserAccRepository) {
        setUserAccRepository(UserAccRepository);
        return this;
    }
    @Override
    public String toString() {
        return "{" +
            " UserAccRepository='" + getUserAccRepository() + "'" +
            "}";
    }
   


@GetMapping("")
public ModelAndView getlanding() {
    ModelAndView mav = new ModelAndView("landingPage.html");
    return mav;
}

@GetMapping("/signup")
public ModelAndView showSignupForm() {
    ModelAndView mav = new ModelAndView("signup.html");
    SignupWrapper signupForm = new SignupWrapper();
    signupForm.setUser(new UserAcc());
    signupForm.setPatient(new Patient());
    signupForm.setDoctor(new Doctor());
    signupForm.setClinic(new Clinic());
    mav.addObject("signupForm", signupForm);
    return mav;
}

public String hashpassword(String password)
{
    String encoddedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
    return encoddedPassword;
}
@PostMapping("/signup")
public ModelAndView processSignupForm(@Valid @ModelAttribute ("signupForm")  SignupWrapper signupForm, BindingResult result, @RequestParam("userType") String userType , @RequestParam("cpassword") String Confirm_pass) {
    UserAcc userAcc = signupForm.getUser(); 
     ModelAndView SignupModel=new ModelAndView("signup.html");
    String encoddedPassword =hashpassword(userAcc.getPass());
    userAcc.setPass(encoddedPassword); 
    ModelAndView LoginModel=new ModelAndView("login.html");


 List<String> errorMessages = new ArrayList<>();


 UserAcc existingUser = UserAccRepository.findByEmail(userAcc.getEmail());
 if (existingUser != null) 
 {
errorMessages.add("Email already exists. Please choose a different email.");
 }

if (userAcc.getPass().length() < 8) 
{
    errorMessages.add("Password must be at least 8 characters long.");
}


if(!BCrypt.checkpw(Confirm_pass, userAcc.getPass()))
{
errorMessages.add("Password and confirm password doesn't match");
}
else
System.err.println("password match");

if (userAcc.getPass().isEmpty())
 {
    errorMessages.add("Password is required");
}


    switch (userType)
    {
        
        case "patient":

        if (result.hasErrors()) 
        {
            System.err.println("fe error fe validations");
                for (ObjectError error : result.getAllErrors()) 
                {
                errorMessages.add(error.getDefaultMessage());
                }
                 SignupModel.addObject("errors", errorMessages);
                 return SignupModel;
        }
            else
            {
            userAcc.setUsertype(new UserTypes(4L));
            Patient patient = signupForm.getPatient();
            // patient.setUserAcc(userAcc);
            this.UserAccRepository.save(userAcc);
            this.patientRepository.save(patient);
            break;
            }
        case "doctor":
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
          userAcc.setUsertype(new UserTypes(3L));
          Doctor doctor = signupForm.getDoctor();
        //   doctor.setUserAcc(userAcc);
           this.UserAccRepository.save(userAcc);
          this.doctorRepository.save(doctor);
          break;
}
        case "clinic":
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

        userAcc.setUsertype(new UserTypes(2L));
        Clinic clinic=signupForm.getClinic();
        // clinic.setUserAcc(userAcc);
        this.UserAccRepository.save(userAcc);
        this.clinicRepository.save(clinic);
            break;
}
        default:
            // Default case
            break;
    }

    return LoginModel;
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
         ModelAndView mav=new ModelAndView("login.html"); 
         UserAcc user=new UserAcc();
         mav.addObject("user", user);
         return mav;
     }

@PostMapping("/test")
public String passtest(@RequestParam("pass") String pass) {
    
    return pass;
}


     @PostMapping("/Login")
     public RedirectView loginprocess(@RequestParam("email") String email, @RequestParam("pass") String pass, HttpSession session) {
        
         UserAcc newUser = this.UserAccRepository.findByEmail(email);

         if (newUser != null) {
             Boolean PasswordsMatch = BCrypt.checkpw(pass, newUser.getPass());
             if (PasswordsMatch) {
                 session.setAttribute("email", newUser.getEmail());
                 session.setAttribute("uid", newUser.getUid());
                 session.setAttribute("usertype", newUser.getUsertype().getName());
                 session.setAttribute("usertypeID", newUser.getUsertype().getUtid());
     
                 if (newUser.getUsertype().getUtid() == 4) {
                     Patient patient = this.patientRepository.findByUserAcc(newUser);
                     session.setAttribute("firstname", patient.getFirstname());
                     session.setAttribute("number", patient.getNumber());
                     session.setAttribute("lastname", patient.getLastname());
                     return new RedirectView("/User/patientHomepage");
                 } else if (newUser.getUsertype().getUtid() == 2) {
                     Clinic clinic = this.clinicRepository.findByUserAcc(newUser);
                     session.setAttribute("firstname", clinic.getCname());
                     session.setAttribute("Location", clinic.getCloc());
                     session.setAttribute("number", clinic.getCnumber());
                     return new RedirectView("/User/clinicHomepage");
                 }
                 else if (newUser.getUsertype().getUtid() == 3) {
                    Doctor doctor = this.doctorRepository.findByUserAcc(newUser);
                    session.setAttribute("firstname", doctor.getFirstname());
                    session.setAttribute("Location", doctor.getLastname());
                    session.setAttribute("number", doctor.getNumber());
                    return new RedirectView("/User/DoctorHomePage");
                }         
             } else {
                 return new RedirectView("/User/Login?error=incorrectPassword"+email);
             }
         }
         return new RedirectView("/User/Login?error=userNotFound"+email);
     }
     
     
     

     @GetMapping("patientHomepage")
     public ModelAndView GetIndex(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("patientHomepage.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        return mav;
     }

     @GetMapping("clinicHomepage")
     public ModelAndView getClinicPage(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("ClinicHomePage.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        return mav;
     }
     @GetMapping("DoctorHomePage")
     public ModelAndView getDoctorPage(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("DoctorHomePage.html");
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
         
     @GetMapping("/logout")
public RedirectView logout(HttpSession session) {
    session.invalidate(); 
    return new RedirectView("/User/Login");
}


     

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") String name, Model model) {
    List<Doctor> Doctors=doctorRepository.findByspecialization(name); 
       ModelAndView mag=new ModelAndView("searchResult.html");
        model.addAttribute("doctors", Doctors);
      return mag;
    }

    @PostMapping("/search")
    public ModelAndView searchresult(@RequestParam("name") String name, Model model) {
    List<Doctor> Doctors=doctorRepository.findByspecialization(name); 
       ModelAndView mag=new ModelAndView("searchResult.html");
        model.addAttribute("doctors", Doctors);
      return mag;
    }



   @GetMapping("/footer")
   public ModelAndView getfooter()
    {
    ModelAndView mav=new ModelAndView("footer.html");
       return mav;
   }

   
}
