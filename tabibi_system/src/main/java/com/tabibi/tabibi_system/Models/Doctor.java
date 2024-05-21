package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Objects;

@Entity

@Table(name = "dr")
public class Doctor extends UserAcc implements Serializable {
    

    @NotEmpty(message = "Doctor first name is Required")

    @Column(name = "firstname")
    private String firstname;

    @NotEmpty(message = "Doctor Last name is Required")
    @Column(name = "lastname")
    private String lastname;

    @NotEmpty(message = "Doctor Specialization  is Required")
    @Column(name = "specialization")
    private String specialization;

    @NotEmpty(message = "Doctor Number is Required")
    @Column(name = "number")
    private String number;

    @NotEmpty(message = "Doctor education is Required")
    @Column(name = "educ")
    private String educ;

   


    

    // @ManyToOne
    // @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = true, updatable = true)
    // private UserAcc userAcc;

    // @ManyToOne
    // @JoinColumn(name = "Cid", referencedColumnName = "uid", insertable = true, updatable = true)
    // private Clinic clinic;


    public Doctor() {
    }

    public Doctor(String firstname, String lastname, String specialization, String number, String educ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialization = specialization;
        this.number = number;
        this.educ = educ;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEduc() {
        return this.educ;
    }

    public void setEduc(String educ) {
        this.educ = educ;
    }

    public Doctor firstname(String firstname) {
        setFirstname(firstname);
        return this;
    }

    public Doctor lastname(String lastname) {
        setLastname(lastname);
        return this;
    }

    public Doctor specialization(String specialization) {
        setSpecialization(specialization);
        return this;
    }

    public Doctor number(String number) {
        setNumber(number);
        return this;
    }

    public Doctor educ(String educ) {
        setEduc(educ);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Doctor)) {
            return false;
        }
        Doctor doctor = (Doctor) o;
        return Objects.equals(firstname, doctor.firstname) && Objects.equals(lastname, doctor.lastname) && Objects.equals(specialization, doctor.specialization) && Objects.equals(number, doctor.number) && Objects.equals(educ, doctor.educ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, specialization, number, educ);
    }

    @Override
    public String toString() {
        return "{" +
            " firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", specialization='" + getSpecialization() + "'" +
            ", number='" + getNumber() + "'" +
            ", educ='" + getEduc() + "'" +
            "}";
    }

    public Doctor getDoctor() {
        return this ;
    }

   
    
}

