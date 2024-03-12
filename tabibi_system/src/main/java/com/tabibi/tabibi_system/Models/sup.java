package com.tabibi.tabibi_system.Models;

public class sup {
    private UserAcc user;
    private Patient patient;
    private Doctor doctor;

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
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
