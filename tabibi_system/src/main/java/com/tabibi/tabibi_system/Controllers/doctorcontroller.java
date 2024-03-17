package com.tabibi.tabibi_system.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Repositories.PatientRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/Doctor")
public class doctorcontroller {
    @Autowired
    PatientRepository patient_repo;

    @GetMapping("/getdata")
    public String getData(@RequestParam  String name) {
       List<Patient> mylist=this.patient_repo.findByfirstname(name);
       if(mylist.size()==0){
          return "no patients fonded ";
       }else{
          String data="";
          for(int i=0;i<mylist.size();i++){
             Patient patient=mylist.get(i);
        data+="<tr onclick='edit(";
        data+=(patient.getPid()+")'>");
        data+="<td>";
 data+=(patient.getPid());
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
    public ModelAndView getinfopage(@RequestParam Long id ,HttpSession session){
       
       Patient patient=this.patient_repo.findBypid(id);
       session.setAttribute("editPid", id);
       session.setAttribute("editAge", patient.getAge());
       session.setAttribute("editAddress",patient.getAddress());
ModelAndView mav=new ModelAndView("patientinfo.html");
mav.addObject("patient", patient);
return mav;
    }

    @GetMapping("/patients")
    public ModelAndView getpatientspage() {
       ModelAndView mav = new ModelAndView("patients.html");
       return mav;
    }
}
