package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clinic")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cid")
    private Long cid;

    @Column(name = "cname")
    private String cname;

    @Column(name = "cloc")
    private String cloc;

    @Column(name = "workhrs")
    private String workhrs;

    @Column(name = "reviews")
    private String reviews;

    @Column(name = "cnumber")
    private String cnumber;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = true, updatable = true)
    private UserAcc userAcc;


    public Clinic() {
    }

    public Clinic(Long cid, String cname, String cloc, String workhrs, String reviews, String cnumber, UserAcc userAcc) {
        this.cid = cid;
        this.cname = cname;
        this.cloc = cloc;
        this.workhrs = workhrs;
        this.reviews = reviews;
        this.cnumber = cnumber;
        this.userAcc = userAcc;
    }

    public Long getCid() {
        return this.cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
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

    public UserAcc getUserAcc() {
        return this.userAcc;
    }

    public void setUserAcc(UserAcc userAcc) {
        this.userAcc = userAcc;
    }

    public Clinic cid(Long cid) {
        setCid(cid);
        return this;
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

    public Clinic userAcc(UserAcc userAcc) {
        setUserAcc(userAcc);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Clinic)) {
            return false;
        }
        Clinic clinic = (Clinic) o;
        return Objects.equals(cid, clinic.cid) && Objects.equals(cname, clinic.cname) && Objects.equals(cloc, clinic.cloc) && Objects.equals(workhrs, clinic.workhrs) && Objects.equals(reviews, clinic.reviews) && Objects.equals(cnumber, clinic.cnumber) && Objects.equals(userAcc, clinic.userAcc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, cname, cloc, workhrs, reviews, cnumber, userAcc);
    }

    @Override
    public String toString() {
        return "{" +
            " cid='" + getCid() + "'" +
            ", cname='" + getCname() + "'" +
            ", cloc='" + getCloc() + "'" +
            ", workhrs='" + getWorkhrs() + "'" +
            ", reviews='" + getReviews() + "'" +
            ", cnumber='" + getCnumber() + "'" +
            ", userAcc='" + getUserAcc() + "'" +
            "}";
    }
    
    
}


