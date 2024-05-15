package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity

@Table(name = "clinic")
public class Clinic extends UserAcc implements Serializable
{

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    

    @NotEmpty(message = "Clinic name is Required")
    @Column(name = "cname")
    private String cname;


    @NotEmpty(message = "Clinic Location is Required")
    @Column(name = "cloc")
    private String cloc;
 
    // @NotEmpty(message = "Clinic hours")
    @Column(name = "workhrs")
    private String workhrs;

    private long test;



    @Column(name = "reviews")
    private String reviews;

    @NotEmpty(message = "Clinic Number is Required")
    @Column(name = "cnumber")
    private String cnumber;




    @Transient
    private Appointment appointment;

    public Clinic() {
    }

    public Clinic( String cname, String cloc, String workhrs, String reviews, String cnumber) {
        this.cname = cname;
        this.cloc = cloc;
        this.workhrs = workhrs;
        this.reviews = reviews;
        this.cnumber = cnumber;
    }

    public Long getCid() {
        return this.test;
    }

 

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCloc() {
        return this.cloc;
    }

    public void setCloc(String cloc) {
        this.cloc = cloc;
    }

    public String getWorkhrs() {
        return this.workhrs;
    }

    public void setWorkhrs(String workhrs) {
        this.workhrs = workhrs;
    }

    public String getReviews() {
        return this.reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getCnumber() {
        return this.cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

 

    public Clinic cname(String cname) {
        setCname(cname);
        return this;
    }

    public Clinic cloc(String cloc) {
        setCloc(cloc);
        return this;
    }

    public Clinic workhrs(String workhrs) {
        setWorkhrs(workhrs);
        return this;
    }

    public Clinic reviews(String reviews) {
        setReviews(reviews);
        return this;
    }

    public Clinic cnumber(String cnumber) {
        setCnumber(cnumber);
        return this;
    }

    public Clinic getClinic() {
        return this;
    }

    
    
}


