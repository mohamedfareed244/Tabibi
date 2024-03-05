package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity

@Table(name = "dr")
public class Doctor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Did")
    private Long did;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "number")
    private String number;

    @Column(name = "educ")
    private String educ;

    @Column(name = "reviews")
    private String reviews;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = true, updatable = true)
    private UserAcc userAcc;

    @ManyToOne
    @JoinColumn(name = "Cid", referencedColumnName = "Cid", insertable = true, updatable = true)
    private Clinic clinic;


    public Doctor() {
    }

    public Doctor(Long did, String firstname, String lastname, String specialization, String number, String educ, String reviews, UserAcc userAcc, Clinic clinic) {
        this.did = did;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialization = specialization;
        this.number = number;
        this.educ = educ;
        this.reviews = reviews;
        this.userAcc = userAcc;
        this.clinic = clinic;
    }

    public Long getDid() {
        return this.did;
    }

    public void setDid(Long did) {
        this.did = did;
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

    public String getReviews() {
        return this.reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public UserAcc getUserAcc() {
        return this.userAcc;
    }

    public void setUserAcc(UserAcc userAcc) {
        this.userAcc = userAcc;
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor did(Long did) {
        setDid(did);
        return this;
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

    public Doctor reviews(String reviews) {
        setReviews(reviews);
        return this;
    }

    public Doctor userAcc(UserAcc userAcc) {
        setUserAcc(userAcc);
        return this;
    }

    public Doctor clinic(Clinic clinic) {
        setClinic(clinic);
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
        return Objects.equals(did, doctor.did) && Objects.equals(firstname, doctor.firstname) && Objects.equals(lastname, doctor.lastname) && Objects.equals(specialization, doctor.specialization) && Objects.equals(number, doctor.number) && Objects.equals(educ, doctor.educ) && Objects.equals(reviews, doctor.reviews) && Objects.equals(userAcc, doctor.userAcc) && Objects.equals(clinic, doctor.clinic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, firstname, lastname, specialization, number, educ, reviews, userAcc, clinic);
    }

    @Override
    public String toString() {
        return "{" +
            " did='" + getDid() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", specialization='" + getSpecialization() + "'" +
            ", number='" + getNumber() + "'" +
            ", educ='" + getEduc() + "'" +
            ", reviews='" + getReviews() + "'" +
            ", userAcc='" + getUserAcc() + "'" +
            ", clinic='" + getClinic() + "'" +
            "}";
    }
    
}

