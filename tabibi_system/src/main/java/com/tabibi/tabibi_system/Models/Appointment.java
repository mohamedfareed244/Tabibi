package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appid")
    private Long appId;
@FutureOrPresent
    @Column(name="date")
    private Date date;

    @NotBlank (message = "please enter a value")
    @Column(name="status")
    private String status ;

    @NotBlank (message = "please enter a value")
    @Pattern(regexp = "\\d{1,2}:\\d{2} (am|pm)", message = "Invalid time format. Please use format like '2:45 pm'.")
    @Column(name="time")
    private String time ;
    

    @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "Price must be a number.")
    @NotNull(message = "please enter a value ")
    @Min(value = 1 , message = "price must be more than 0") 
    @Column(name="price")
    private String price;

    @ManyToOne
    @JoinColumn(name = "Did", referencedColumnName = "uid", insertable = true, updatable = true)
    private Doctor doctor;



    @ManyToOne
    @JoinColumn(name = "Cid", referencedColumnName = "uid", insertable = true, updatable = true)
    private Clinic clinic;

@NotNull(message = "please enter a value ")
@Min(value = 0 , message = "please enter a value between 1 and 20") 
@Max(value = 25 , message = "please enter a value between 1 and 20") 
    @Column(name="capacity")
    private int capacity ;

    @Column(name = "booked")
    private int booked ;



    public Appointment() {
    }

    public Appointment(Long appId, Date date, String status, String time, String price, Doctor doctor, Clinic clinic, int capacity, int booked) {
        this.appId = appId;
        this.date = date;
        this.status = status;
        this.time = time;
        this.price = price;
        this.doctor = doctor;
        this.clinic = clinic;
        this.capacity = capacity;
        this.booked = booked;
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


    public Clinic getClinic() {
        return this.clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBooked() {
        return this.booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
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



    public Appointment clinic(Clinic clinic) {
        setClinic(clinic);
        return this;
    }

    public Appointment capacity(int capacity) {
        setCapacity(capacity);
        return this;
    }

    public Appointment booked(int booked) {
        setBooked(booked);
        return this;
    }

    public int getPlacesLeft() {
        return capacity - booked;
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
            ", clinic='" + getClinic() + "'" +
            ", capacity='" + getCapacity() + "'" +
            ", booked='" + getBooked() + "'" +
            "}";
    }

  

   
   
}
