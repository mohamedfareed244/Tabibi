package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appid")
    private Long appId;

    @ManyToOne
    @JoinColumn(name = "Did", referencedColumnName = "Did", insertable = true, updatable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "Pid", referencedColumnName = "Pid", insertable = true, updatable = true)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "Cid", referencedColumnName = "Cid", insertable = true, updatable = true)
    private Clinic clinic;


    public Appointment() {
    }

    public Appointment(Long appId, Doctor doctor, Patient patient, Clinic clinic) {
        this.appId = appId;
        this.doctor = doctor;
        this.patient = patient;
        this.clinic = clinic;
    }

    public Long getAppId() {
        return this.appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Appointment appId(Long appId) {
        setAppId(appId);
        return this;
    }

    public Appointment doctor(Doctor doctor) {
        setDoctor(doctor);
        return this;
    }

    public Appointment patient(Patient patient) {
        setPatient(patient);
        return this;
    }

    public Appointment clinic(Clinic clinic) {
        setClinic(clinic);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Appointment)) {
            return false;
        }
        Appointment appointment = (Appointment) o;
        return Objects.equals(appId, appointment.appId) && Objects.equals(doctor, appointment.doctor) && Objects.equals(patient, appointment.patient) && Objects.equals(clinic, appointment.clinic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, doctor, patient, clinic);
    }

    @Override
    public String toString() {
        return "{" +
            " appId='" + getAppId() + "'" +
            ", doctor='" + getDoctor() + "'" +
            ", patient='" + getPatient() + "'" +
            ", clinic='" + getClinic() + "'" +
            "}";
    }
   
}
