package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appid")
    private Long appId;

    @Column(name="date")
    private Date date;

    @Column(name="status")
    private String status ;

    @Column(name="time")
    private String time ;
    
    @Column(name="price")
    private String price;

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

    public Appointment(Long appId, Date date, String status, String time, String price, Doctor doctor, Patient patient, Clinic clinic) {
        this.appId = appId;
        this.date = date;
        this.status = status;
        this.time = time;
        this.price = price;
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public Appointment date(Date date) {
        setDate(date);
        return this;
    }

    public Appointment status(String status) {
        setStatus(status);
        return this;
    }

    public Appointment time(String time) {
        setTime(time);
        return this;
    }

    public Appointment price(String price) {
        setPrice(price);
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
        return Objects.equals(appId, appointment.appId) && Objects.equals(date, appointment.date) && Objects.equals(status, appointment.status) && Objects.equals(time, appointment.time) && Objects.equals(price, appointment.price) && Objects.equals(doctor, appointment.doctor) && Objects.equals(patient, appointment.patient) && Objects.equals(clinic, appointment.clinic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, date, status, time, price, doctor, patient, clinic);
    }

    @Override
    public String toString() {
        return "{" +
            " appId='" + getAppId() + "'" +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", time='" + getTime() + "'" +
            ", price='" + getPrice() + "'" +
            ", doctor='" + getDoctor() + "'" +
            ", patient='" + getPatient() + "'" +
            ", clinic='" + getClinic() + "'" +
            "}";
    }


  

   
   
}
