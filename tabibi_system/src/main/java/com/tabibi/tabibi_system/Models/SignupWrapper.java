package com.tabibi.tabibi_system.Models;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class SignupWrapper {

@Valid
@NotNull
    private UserAcc user;

    @Valid
    private Patient patient;

    @Valid
    private Clinic clinic;

    @Valid
    private Doctor doctor;

    
    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
        this.patient=null;
        this.doctor=null;
    }


    public UserAcc getUser() {
        return user;
    }

    public void setUser(UserAcc user) {
        this.user = user;
    }
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        this.clinic=null;
        this.patient=null;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.doctor = null;
        this.clinic = null;
    }
}
