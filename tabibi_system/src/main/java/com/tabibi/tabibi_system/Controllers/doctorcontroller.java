package com.tabibi.tabibi_system.Controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.Diagnosis;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PagesRepository;
import com.tabibi.tabibi_system.Repositories.PatientRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;
import com.tabibi.tabibi_system.Repositories.DiagnosisRepository;


import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/Doctor")
public class doctorcontroller {
    @Autowired
    PatientRepository patient_repo;
     @Autowired 
    private DoctorRepository doctorRepository;
    @Autowired 
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private UserAccRepository userAccRepository;
    @Autowired
    UserTypeRepository user_type_repo;
 
    @Autowired
    PagesRepository pages_repo;
    @Autowired
    public UserTypePagesRepository page_type_repo;
admincontroller admincontroller=new admincontroller();


    @GetMapping("/getdata")
    public String getData(@RequestParam  String name) {
       List<Patient> mylist=this.patient_repo.findByfirstname(name);
       if(mylist.size()==0){
          return "no patients found ";
       }else{
          String data="";
          for(int i=0;i<mylist.size();i++){
             Patient patient=mylist.get(i);
        data+="<tr onclick='edit(";
        data+=(patient.getUid()+")'>");
        data+="<td>";
 data+=(patient.getUid());
 data+="</td>";
 data+="<td>";
 data+=(patient.getFirstname());
 data+="</td>";
 data+="<td>";
 data+=(patient.getLastname());
 data+="</td>";
 data+="<td>";
 data+=(patient.getNumber());
 data+="</td> </tr>";
 
          }
          return data;
       }
     
    }
    @GetMapping("/info")
    public ModelAndView getinfopage(@RequestParam Integer id ,HttpSession session){
       
       Patient patient=this.patient_repo.findByUid(id);
       session.setAttribute("editPid", id);
       session.setAttribute("editAge", patient.getAge());
       session.setAttribute("editAddress",patient.getAddress());
      ModelAndView mav=new ModelAndView("patientinfo.html");
    UserAcc userAcc = userAccRepository.findByUid(id);
    List<Diagnosis> diagnoses = diagnosisRepository.findByUserAcc(userAcc);
      mav.addObject("diagnoses", diagnoses);
      mav.addObject("patient", patient);
      return mav;
    }

    @GetMapping("/patients")
    public ModelAndView getpatientspage() {
       ModelAndView mav = new ModelAndView("patients.html");
       return mav;
    }
     @GetMapping("accountSettings")
     public ModelAndView getSettings(HttpSession session)
     {
      
    ModelAndView mav= com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session, "AccountSettings.html", user_type_repo, page_type_repo);

        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        return mav;
     }
     @GetMapping("Profile")
     public ModelAndView getProfile(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("profile.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        mav.addObject("lastname",(String) session.getAttribute("lastname"));
        mav.addObject("number",(String) session.getAttribute("number"));
        return mav;
     }
     @GetMapping("EditProfile")
     public ModelAndView getEditProfile(HttpSession session)
     {
        ModelAndView mav=new ModelAndView("EditDoctorProfile.html");
        mav.addObject("email",(String) session.getAttribute("email"));
        mav.addObject("firstname",(String) session.getAttribute("firstname"));
        mav.addObject("lastname",(String) session.getAttribute("lastname"));
        mav.addObject("number",(String) session.getAttribute("number"));
        mav.addObject("uid",(Integer) session.getAttribute("uid"));
        mav.addObject("specialization",(String) session.getAttribute("specialization"));
        mav.addObject("education",(String) session.getAttribute("education"));

        


        return mav;
     }
     @PostMapping("/EditProfile")
     public RedirectView editprocess(HttpSession session,@RequestParam("email") String email,
     @RequestParam("firstname") String firstname,
     @RequestParam("lastname") String lastname,
     @RequestParam("number") String number,
     @RequestParam("specialization") String specialization,
     @RequestParam("education") String education ) 
     {
         Integer uid = (Integer)session.getAttribute("uid");
         Doctor DoctorEdit = this.doctorRepository.findByUid(uid);
         if (DoctorEdit != null) {
             session.setAttribute("email", email);
             session.setAttribute("firstname", firstname);
             session.setAttribute("lastname", lastname);
             session.setAttribute("number", number);
             session.setAttribute("specialization", specialization);
             session.setAttribute("education", education);

             
             DoctorEdit.setFirstname(firstname);
             DoctorEdit.setLastname(lastname);
             DoctorEdit.setNumber(number);
             DoctorEdit.setEmail(email);
             DoctorEdit.setSpecialization(specialization);
             DoctorEdit.setEduc(education);
             this.doctorRepository.save(DoctorEdit);
             
             return new RedirectView("/User/DoctorHomePage");
         }
         return new RedirectView("/Doctor/EditProfile?error=error");
     
     }
     
     @GetMapping("/deleteAccount")
     public RedirectView deleteAccount(HttpSession session) {
         Integer uid = (Integer) session.getAttribute("uid");
         Doctor DoctorDelete = this.doctorRepository.findByUid(uid);
         if (DoctorDelete != null) {
                 this.doctorRepository.delete(DoctorDelete);
                 session.invalidate(); 
                 return new RedirectView("/User/login"); 
         }
         return new RedirectView("/Doctor/Profile"); 
     }

     @PostMapping("/addDiagnose")
     public RedirectView DiagnosePatient(
     @RequestParam("diagnosename") String diagnosename,
     @RequestParam("treatment") String treatment,
     HttpSession session){

      Diagnosis diagnosis=new Diagnosis();
      diagnosis.setDiagnosisName(diagnosename);
      diagnosis.setTreatment(treatment);
      diagnosis.setUserAcc(this.userAccRepository.findByUid((Integer)session.getAttribute("editPid")));
      this.diagnosisRepository.save(diagnosis);
      
        return new RedirectView("/User/DoctorHomePage");

     }


         @GetMapping("/deleteDiagnose/{id}")
    public RedirectView deleteDiagnose(@PathVariable("id") Long id,HttpSession session) {
        Diagnosis diagnosis = this.diagnosisRepository.findByDiagnosisId(id);
        if(diagnosis!= null)
        {
        this.diagnosisRepository.delete(diagnosis);
        }
        return new RedirectView("/User/DoctorHomePage");
    }
 
}
