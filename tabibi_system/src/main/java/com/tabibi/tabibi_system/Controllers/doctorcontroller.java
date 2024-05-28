package com.tabibi.tabibi_system.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
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
import com.tabibi.tabibi_system.Models.Feedback;
import com.tabibi.tabibi_system.Models.Medicine;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Models.UserLog;
import com.tabibi.tabibi_system.Models.UserTypes;
import com.tabibi.tabibi_system.Repositories.DoctorRepository;
import com.tabibi.tabibi_system.Repositories.PagesRepository;
import com.tabibi.tabibi_system.Repositories.PatientRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;
import com.tabibi.tabibi_system.Repositories.UserLogRepository;
import com.tabibi.tabibi_system.Repositories.UserTypePagesRepository;
import com.tabibi.tabibi_system.Repositories.UserTypeRepository;
import com.tabibi.tabibi_system.Services.MedicineService;
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
    MedicineService medicineService;

    @Autowired
    PagesRepository pages_repo;
    @Autowired
    public UserTypePagesRepository page_type_repo;

    @Autowired
    public UserLogRepository userlog;
    admincontroller admincontroller = new admincontroller();

    @GetMapping("/getdata")
    public String getData(@RequestParam String name) {
        System.out.println(name);
        List<Patient> mylist = this.patient_repo.findByfirstname(name);
        if (mylist.size() == 0) {
            return "no patients found ";
        } else {
            String data = "";
            for (int i = 0; i < mylist.size(); i++) {
                Patient patient = mylist.get(i);
                data += "<tr onclick='edit(";
                data += (patient.getUid() + ")'>");
                data += "<td>";
                data += (patient.getUid());
                data += "</td>";
                data += "<td>";
                data += (patient.getFirstname());
                data += "</td>";
                data += "<td>";
                data += (patient.getLastname());
                data += "</td>";
                data += "<td>";
                data += (patient.getNumber());
                data += "</td> </tr>";

            }
            return data;
        }

    }

    @GetMapping("/info")
    public ModelAndView getinfopage(@RequestParam Integer id, HttpSession session) {
        Patient patient = this.patient_repo.findByUid(id);
        session.setAttribute("editPid", id);
        session.setAttribute("editAge", patient.getAge());
        session.setAttribute("editAddress", patient.getAddress());
        ModelAndView mav = new ModelAndView("patientinfo.html");
        UserAcc patientAccount = userAccRepository.findByUid(id);
        UserAcc doctorAccount = userAccRepository.findByUid((Integer) session.getAttribute("uid"));
        List<Diagnosis> diagnoses = diagnosisRepository.findByUserAccAndUser(patientAccount, doctorAccount);
        List<Medicine> medicines = medicineService.findAll();
        mav.addObject("diagnoses", diagnoses);
        mav.addObject("patient", patient);
        mav.addObject("medicines", medicines);
        return mav;
    }

    @GetMapping("/medicine")
    public ModelAndView getMedicines(HttpSession session) {
        ModelAndView mav = admincontroller.preparenavigation(session, "ViewMedicines.html", user_type_repo, page_type_repo);
        List<Medicine> medicines = medicineService.findAll();
        mav.addObject("medicines", medicines);
        return mav;
    }


    @GetMapping("/medicine/add")
    public ModelAndView addMedicines(HttpSession session) {
        ModelAndView mav = admincontroller.preparenavigation(session, "addMedicine", user_type_repo, page_type_repo);
        Medicine medicine = new Medicine();
        mav.addObject("medicine", medicine);
        return mav;
    }

    @PostMapping("/medicine/add")
    public String addMedicines(@ModelAttribute Medicine medicine) {
        this.medicineService.save(medicine);
        return "added";
    }

    @GetMapping("/medicine/edit/{id}")
    public ModelAndView editMedicine(@PathVariable("id") Integer id, HttpSession session) {
        ModelAndView mav = admincontroller.preparenavigation(session, "editMedicine", user_type_repo, page_type_repo);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        Medicine medicine = medicineService.findById(id);
        mav.addObject("medicine", medicine);
        return mav;
    }
    
    @PostMapping("/medicine/edit")
    public RedirectView editMedicine(@ModelAttribute Medicine medicine) {
        medicineService.update(medicine);
        return new RedirectView("/Doctor/medicine");
    }
    
    



    @PostMapping("/medicine/delete")
public RedirectView deleteFeedback(@RequestParam("id") Integer id) {
    medicineService.delete(id);
    return new RedirectView("/Doctor/medicine");
}

    @GetMapping("/patients")
    public ModelAndView getpatientspage(HttpSession session) {
        ModelAndView mav = com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session,
                "patients.html", user_type_repo, page_type_repo);

        return mav;
    }

    @GetMapping("accountSettings")
    public ModelAndView getSettings(HttpSession session) {

        ModelAndView mav = com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session,
                "DoctorAccountSettings.html", user_type_repo, page_type_repo);

        mav.addObject("email", (String) session.getAttribute("email"));
        mav.addObject("firstname", (String) session.getAttribute("firstname"));
        return mav;
    }

    @GetMapping("Profile")
    public ModelAndView getProfile(HttpSession session) {
        ModelAndView mav = com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session,
                "DoctorProfile.html", user_type_repo, page_type_repo);

        mav.addObject("email", (String) session.getAttribute("email"));
        mav.addObject("firstname", (String) session.getAttribute("firstname"));
        mav.addObject("lastname", (String) session.getAttribute("lastname"));
        mav.addObject("number", (String) session.getAttribute("number"));
        mav.addObject("specialization", (String) session.getAttribute("specialization"));
        mav.addObject("education", (String) session.getAttribute("education"));

        return mav;
    }

    @GetMapping("EditProfile")
    public ModelAndView getEditProfile(HttpSession session) {
        ModelAndView mav = com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session,
                "EditDoctorProfile.html", user_type_repo, page_type_repo);

        mav.addObject("email", (String) session.getAttribute("email"));
        mav.addObject("firstname", (String) session.getAttribute("firstname"));
        mav.addObject("lastname", (String) session.getAttribute("lastname"));
        mav.addObject("number", (String) session.getAttribute("number"));
        mav.addObject("uid", (Integer) session.getAttribute("uid"));
        mav.addObject("specialization", (String) session.getAttribute("specialization"));
        mav.addObject("education", (String) session.getAttribute("education"));

        return mav;
    }

    @PostMapping("/EditProfile")
    public RedirectView editprocess(HttpSession session,
            @RequestParam("email") String email,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("number") String number,
            @RequestParam("specialization") String specialization,
            @RequestParam("education") String education,
            @RequestParam(value = "currentPassword", required = false) String currentPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            @RequestParam(value = "confirmNewPassword", required = false) String confirmNewPassword) {
        Integer uid = (Integer) session.getAttribute("uid");
        Doctor doctor = this.doctorRepository.findByUid(uid);

        if (doctor != null) {

            doctor.setFirstname(firstname);
            doctor.setLastname(lastname);
            doctor.setNumber(number);
            doctor.setEmail(email);
            doctor.setSpecialization(specialization);
            doctor.setEduc(education);
            session.setAttribute("email", doctor.getEmail());
            session.setAttribute("firstname", doctor.getFirstname());
            session.setAttribute("lastname", doctor.getLastname());
            session.setAttribute("number", doctor.getNumber());
            session.setAttribute("specialization", doctor.getSpecialization());
            session.setAttribute("education", doctor.getEduc());

            if (currentPassword != null && !currentPassword.isEmpty() &&
                    newPassword != null && !newPassword.isEmpty() &&
                    confirmNewPassword != null && !confirmNewPassword.isEmpty()) {

                if (BCrypt.checkpw(currentPassword, doctor.getPass())) {
                    if (newPassword.equals(confirmNewPassword)) {
                        String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                        doctor.setPass(hashedNewPassword);
                    } else {
                        return new RedirectView("/Doctor/EditProfile?error=passwordMismatch");
                    }
                } else {
                    return new RedirectView("/Doctor/EditProfile?error=incorrectPassword");
                }
            }

            this.doctorRepository.save(doctor);
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
            return new RedirectView("/User/Login");
        }
        return new RedirectView("/Doctor/Profile");
    }

    @PostMapping("/addDiagnose")
    public RedirectView DiagnosePatient(
            @RequestParam("diagnosename") String diagnosename,
            @RequestParam(value = "medicineName", required = false) String medicineName,
            @RequestParam(value = "notes", required = false) String notes,
            HttpSession session) {

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisName(diagnosename);
        diagnosis.setMedicineName(medicineName);
        diagnosis.setNotes(notes);
        diagnosis.setUserAcc(this.userAccRepository.findByUid((Integer) session.getAttribute("editPid")));
        diagnosis.setUser(this.userAccRepository.findByUid((Integer) session.getAttribute("uid")));
        this.diagnosisRepository.save(diagnosis);

        return new RedirectView("/User/DoctorHomePage");

    }

    @GetMapping("/deleteDiagnose/{id}")
    public RedirectView deleteDiagnose(@PathVariable("id") Long id, HttpSession session) {
        Diagnosis diagnosis = this.diagnosisRepository.findByDiagnosisId(id);
        if (diagnosis != null) {
            this.diagnosisRepository.delete(diagnosis);
        }
        return new RedirectView("/User/DoctorHomePage");
    }

    @GetMapping("/editDiagnose/{id}")
    public ModelAndView editDiagnose(@PathVariable("id") Long id, HttpSession session) {
        Diagnosis diagnosis = this.diagnosisRepository.findByDiagnosisId(id);
        ModelAndView mav = com.tabibi.tabibi_system.Controllers.admincontroller.preparenavigation(session,
                "editDiagnoses.html", user_type_repo, page_type_repo);
                List<Medicine> medicines = medicineService.findAll();
        mav.addObject("medicines", medicines);
        mav.addObject("diagnosis", diagnosis);
        return mav;
    }

    @PostMapping("/editDiagnose")
    public RedirectView editDiagnossisprocess(HttpSession session, @RequestParam("diagnosisName") String diagnosisName,
    @RequestParam(value = "medicineName", required = false) String medicineName,
    @RequestParam(value = "notes", required = false) String notes,
            @RequestParam("diagnosisId") Long diagnosisId) {
        // Integer uid = (Integer)session.getAttribute("uid");
        // Doctor DoctorEdit = this.doctorRepository.findByUid(uid);

        Diagnosis diagnosis = this.diagnosisRepository.findByDiagnosisId(diagnosisId);
        if (diagnosis != null) {

            diagnosis.setDiagnosisName(diagnosisName);
            diagnosis.setNotes(notes);
            diagnosis.setMedicineName(medicineName);
            this.diagnosisRepository.save(diagnosis);

            return new RedirectView("/User/DoctorHomePage");
        }
        return new RedirectView("/Doctor/editDiagnose?error=error");

    }

}
