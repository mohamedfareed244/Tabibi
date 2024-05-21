package com.tabibi.tabibi_system.Controllers;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.Diagnosis;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Repositories.DiagnosisRepository;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PagesRepository;
import com.tabibi.tabibi_system.Repositories.PatientRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/patient")
public class PatientController {
        @Autowired
    private UserAccRepository UserAccRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    UserTypeRepository user_type_repo;
    @Autowired
    public UserTypePagesRepository page_type_repo;
    @Autowired
    PagesRepository pages_repo;
 @GetMapping("accountSettings")
     public ModelAndView getSettings(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("accountSettings.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        return mav;
     }
     @GetMapping("Profile")
     public ModelAndView getProfile(HttpSession session)
     {
     ModelAndView mav= admincontroller.preparenavigation(session, "profile.html", user_type_repo, page_type_repo);

        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        mav.addObject("lastname",(String) session.getAttribute("lastname"));
        mav.addObject("number",(String) session.getAttribute("number"));
        return mav;
     }
     @GetMapping("EditProfile")
     public ModelAndView getEditProfile(HttpSession session)
     {
     ModelAndView mav= admincontroller.preparenavigation(session, "editProfile.html", user_type_repo, page_type_repo);

        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        mav.addObject("lastname",(String) session.getAttribute("lastname"));
        mav.addObject("number",(String) session.getAttribute("number"));
        mav.addObject("uid",(Integer) session.getAttribute("uid"));

        return mav;
     }
     @PostMapping("/EditProfile")
     public RedirectView editprocess(HttpSession session,@RequestParam("email") String email,
     @RequestParam("firstname") String firstname,
     @RequestParam("lastname") String lastname,
     @RequestParam("number") String number) 
     {
         Integer uid = (Integer)session.getAttribute("uid");
         Patient PatientEdit = this.patientRepository.findByUid(uid);
         if (PatientEdit != null) {
             session.setAttribute("email", email);
             session.setAttribute("firstname", firstname);
             session.setAttribute("lastname", lastname);
             session.setAttribute("number", number);
             
             PatientEdit.setFirstname(firstname);
             PatientEdit.setLastname(lastname);
             PatientEdit.setNumber(number);
             PatientEdit.setEmail(email);
             this.patientRepository.save(PatientEdit);
             
             return new RedirectView("/User/patientHomepage");
         }
         return new RedirectView("/patient/EditProfile?error=incorrectPassword");
     
     }
     @PostMapping("/edit")
     public RedirectView editpatient(
     @RequestParam("firstname") String firstname,
     @RequestParam("lastname") String lastname,
     @RequestParam("number") String number,
     @RequestParam("address") String address,
     @RequestParam("gender") String gender,
     HttpSession session){

        Patient PatientEdit = this.patientRepository.findByUid((Integer)session.getAttribute("editPid"));
        if (PatientEdit == null) {
            return new RedirectView("/User/Login");
        }
        
        PatientEdit.setFirstname(firstname);
        PatientEdit.setLastname(lastname);
        PatientEdit.setNumber(number);
        PatientEdit.setAddress(address);
        PatientEdit.setGender(gender);
        this.patientRepository.save(PatientEdit);
        return new RedirectView("/User/DoctorHomePage");

     }
     
     @GetMapping("/deleteAccount")
     public RedirectView deleteAccount(HttpSession session) {
         Integer uid = (Integer) session.getAttribute("uid");
         Patient PatientDelete = this.patientRepository.findByUid(uid);
         if (PatientDelete != null) {
                 this.patientRepository.delete(PatientDelete);
                 session.invalidate(); 
                 return new RedirectView("/User/Login"); 
         }
         return new RedirectView("/patient/Profile"); 
     }

    @GetMapping("/diagnoses")
public ModelAndView viewDiagnoses(HttpSession session) {
    ModelAndView mav= admincontroller.preparenavigation(session, "ViewDiagnosis.html", user_type_repo, page_type_repo);

    Integer patientId = (Integer)session.getAttribute("uid");
    UserAcc userAcc = this.UserAccRepository.findByUid(patientId);
    List<Diagnosis> diagnoses = this.diagnosisRepository.findByUserAcc(userAcc);
    for (Diagnosis diagnosis : diagnoses) {
        if (diagnosis.getUser() != null) {
            Doctor doctor = this.doctorRepository.findByUid(diagnosis.getUser().getUid());
            diagnosis.setDoctorName(doctor.getFirstname());
        }
    }
    mav.addObject("diagnoses", diagnoses);
    Patient patient=this.patientRepository.findByUid((Integer)session.getAttribute("uid"));
    mav.addObject("patient", patient);
    return mav;

}

    
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") String name, Model model,HttpSession session) {
    List<Doctor> Doctors=doctorRepository.findByspecialization(name); 
    ModelAndView mag= admincontroller.preparenavigation(session, "searchResult.html", user_type_repo, page_type_repo);

        model.addAttribute("doctors", Doctors);
      return mag;
    }

    @PostMapping("/search")
    public ModelAndView searchresult(@RequestParam("name") String name, Model model,HttpSession session) {
    List<Doctor> Doctors=doctorRepository.findByspecialization(name); 
    ModelAndView mag= admincontroller.preparenavigation(session, "searchResult.html", user_type_repo, page_type_repo);

        model.addAttribute("doctors", Doctors);
      return mag;
    }

//      @PostMapping("/deletePatient")
// public RedirectView deletePatient(HttpSession session) {
//     Integer uid = (Integer) session.getAttribute("uid");
//     UserAcc userAcc = this.UserAccRepository.findByUid(uid);
//     if (userAcc != null) {
//         Patient patient = this.patientRepository.findByUserAcc(userAcc);
//         if (patient != null) {
//             this.patientRepository.delete(patient);
//             this.UserAccRepository.delete(userAcc);
//             session.invalidate(); 
//             return new RedirectView("/login");
//         }
//     }
//     return new RedirectView("/patient/Profile");
// }

    
}
