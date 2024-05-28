package com.tabibi.tabibi_system.Controllers;

import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Models.Workplaces;
import com.tabibi.tabibi_system.Repositories.ClinicRepository;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;
import com.tabibi.tabibi_system.Repositories.WorkplacesRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Clinic")
public class ClinicController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private WorkplacesRepository workplacesRepository;
    @Autowired
    UserTypeRepository user_type_repo;

    @Autowired
    PagesRepository pages_repo;
    @Autowired
    public UserTypePagesRepository page_type_repo;

    @GetMapping("DoctorRegistration")
    public ModelAndView DoctorRegistration(HttpSession session) {
        ModelAndView mav = admincontroller.preparenavigation(session, "DoctorRegistration", user_type_repo,
                page_type_repo);
        Doctor doctor = new Doctor();
        mav.addObject("doctor", doctor);
        return mav;
    }

    public String hashpassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    @PostMapping("DoctorRegistration")
    public ModelAndView processSignupForm(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result,
            @RequestParam("cpassword") String confirmPass, HttpSession session) {
        ModelAndView signupModel = admincontroller.preparenavigation(session, "DoctorRegistration.html", user_type_repo,
                page_type_repo);

        List<String> errorMessages = new ArrayList<>();

        Doctor existingUser = doctorRepository.findByEmail(doctor.getEmail());
        if (existingUser != null) {
            result.rejectValue("email", "error.patient", "Email already exists. Please choose a different email.");
        }
    
        if (!doctor.getPass().equals(confirmPass)) {
            result.rejectValue("pass", "error.patient", "Password and Confirm Password must match.");
        }
    
        if (doctor.getPass().length() < 8) {
            result.rejectValue("pass", "error.patient", "Password must be at least 8 characters long.");
        }
    
        if (doctor.getPass().isEmpty()) {
            result.rejectValue("pass", "error.patient", "Password is required.");
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            signupModel.addObject("errors", errorMessages);
            return signupModel;
        } else {
            Doctor doctorr = doctor.getDoctor();
            String encodedPassword = hashpassword(doctor.getPass());
            doctorr.setPass(encodedPassword);
            doctorr.setUsertype(new UserTypes(3L));
            this.doctorRepository.save(doctorr);
        }

        return new ModelAndView("redirect:/User/clinicHomepage");
    }

    @GetMapping("Profile")
    public ModelAndView getProfile(HttpSession session) {
        ModelAndView mav = admincontroller.preparenavigation(session, "ClinicProfile.html", user_type_repo,
                page_type_repo);

        mav.addObject("email", session.getAttribute("email"));
        mav.addObject("firstname", session.getAttribute("firstname"));
        mav.addObject("location", session.getAttribute("Location"));
        mav.addObject("number", session.getAttribute("number"));

        return mav;
    }

    @GetMapping("accountSettings")
    public ModelAndView getSettings(HttpSession session) {
        ModelAndView mav = admincontroller.preparenavigation(session, "ClinicAccountSettings.html", user_type_repo,
                page_type_repo);

        mav.addObject("email", session.getAttribute("email"));
        mav.addObject("firstname", session.getAttribute("firstname"));
        return mav;
    }

    @GetMapping("EditProfile")
    public ModelAndView getEditProfile(HttpSession session) {
        ModelAndView mav = admincontroller.preparenavigation(session, "EditClinicProfile.html", user_type_repo,
                page_type_repo);

        mav.addObject("email", session.getAttribute("email"));
        mav.addObject("firstname", session.getAttribute("firstname"));
        mav.addObject("location", session.getAttribute("Location"));
        mav.addObject("number", session.getAttribute("number"));
        mav.addObject("uid", session.getAttribute("uid"));

        return mav;
    }

    @PostMapping("/EditProfile")
    public RedirectView editprocess(HttpSession session, @RequestParam("email") String email,
            @RequestParam("firstname") String firstname,
            @RequestParam("location") String location,
            @RequestParam("number") String number,
            @RequestParam(value = "currentPassword", required = false) String currentPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            @RequestParam(value = "confirmNewPassword", required = false) String confirmNewPassword) {
        int uid = (int) session.getAttribute("uid");
        Clinic clinicEdit = this.clinicRepository.findByUid(uid);
        if (clinicEdit != null) {


            clinicEdit.setCname(firstname);
            clinicEdit.setEmail(email);
            clinicEdit.setCloc(location);
            clinicEdit.setCnumber(number);

            session.setAttribute("email", email);
            session.setAttribute("firstname", firstname);
            session.setAttribute("Location", location);
            session.setAttribute("number", number);
            if (currentPassword != null && !currentPassword.isEmpty() && 
            newPassword != null && !newPassword.isEmpty() &&
            confirmNewPassword != null && !confirmNewPassword.isEmpty())
             {
            
            if (BCrypt.checkpw(currentPassword, clinicEdit.getPass())) {
                if (newPassword.equals(confirmNewPassword)) {
                    String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                    clinicEdit.setPass(hashedNewPassword);
                } else {
                    return new RedirectView("/Clinic/EditProfile?error=passwordMismatch");
                }
            } else {
                return new RedirectView("/Clinic/EditProfile?error=incorrectPassword");
            }
        }
            clinicRepository.save(clinicEdit);

            return new RedirectView("/User/clinicHomepage");
        }
        return new RedirectView("/Clinic/EditProfile?error=error");
    }

    @GetMapping("/deleteAccount")
    public RedirectView deleteAccount(HttpSession session) {
        int uid = (int) session.getAttribute("uid");
        Clinic clinicDelete = this.clinicRepository.findByUid(uid);
        if (clinicDelete != null) {
            this.clinicRepository.delete(clinicDelete);
            session.invalidate();
            return new RedirectView("/User/Login");
        }
        return new RedirectView("/Clinic/Profile");
    }

    @GetMapping("/assignDoctor")
    public ModelAndView viewClinic(HttpSession session) {
        
        ModelAndView mav = admincontroller.preparenavigation(session, "assignDoctorsView.html", user_type_repo,
        page_type_repo);



        int uid = (int) session.getAttribute("uid");

        Clinic clinic = clinicRepository.findByUid(uid);

        List<Doctor> assignedDoctors = workplacesRepository.findByClinic(clinic)
                .stream()
                .map(Workplaces::getDoctor)
                .collect(Collectors.toList());

        List<Doctor> allDoctors = doctorRepository.findAll();
        List<Doctor> unassignedDoctors = allDoctors.stream()
                .filter(doctor -> !assignedDoctors.contains(doctor))
                .collect(Collectors.toList());
        int clinic_id = clinic.getUid();
        mav.addObject("clinic", clinic);
        mav.addObject("clinic_id", clinic_id);
        mav.addObject("assignedDoctors", assignedDoctors);
        mav.addObject("unassignedDoctors", unassignedDoctors);
        System.out.println("------------------- DEBUG ------------------------");
        System.out.println("clinic : " + clinic);
        System.out.println("clinic id : " + clinic_id);
        System.out.println("assigned : " + assignedDoctors);
        System.out.println("unassigned : " + unassignedDoctors);
        System.out.println("all : " + allDoctors);
        return mav;

    }

    @PostMapping("/assignDoctor")
    public RedirectView assignDoctor(@RequestParam("doctorId") int doctorId, @RequestParam("clinicId") int clinicId,
            HttpSession session) {

        Clinic clinic = clinicRepository.findByUid(clinicId);
        Doctor doctor = doctorRepository.findByUid(doctorId);

        if (clinic != null && doctor != null) {
            if (!workplacesRepository.existsByDoctorAndClinic(doctor, clinic)) {
                Workplaces workplace = new Workplaces();
                workplace.setDoctor(doctor);
                workplace.setClinic(clinic);
                workplacesRepository.save(workplace);
            }
        }
        return new RedirectView("/Clinic/assignDoctor");
    }
}
