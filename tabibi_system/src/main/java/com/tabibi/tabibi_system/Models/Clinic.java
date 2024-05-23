package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "clinic")
public class Clinic extends UserAcc implements Serializable {

    @NotEmpty(message = "Clinic name is Required")
    @Column(name = "cname")
    private String cname;

    @NotEmpty(message = "Clinic Location is Required")
    @Column(name = "cloc")
    private String cloc;

    @NotEmpty(message = "Clinic Number is Required")
    @Column(name = "cnumber")
    private String cnumber;

    @Transient
    private Appointment appointment;

    public Clinic() {}

    public Clinic(String cname, String cloc, String cnumber, Appointment appointment) {
        this.cname = cname;
        this.cloc = cloc;
        this.cnumber = cnumber;
        this.appointment = appointment;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCloc() {
        return cloc;
    }

    public void setCloc(String cloc) {
        this.cloc = cloc;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    public Clinic cname(String cname) {
        setCname(cname);
        return this;
    }

    public Clinic cloc(String cloc) {
        setCloc(cloc);
        return this;
    }

    public Clinic cnumber(String cnumber) {
        setCnumber(cnumber);
        return this;
    }

    public Clinic appointment(Appointment appointment) {
        setAppointment(appointment);
        return this;
    }
    public Clinic getClinic()
    {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clinic)) return false;
        Clinic clinic = (Clinic) o;
        return Objects.equals(cname, clinic.cname) && Objects.equals(cloc, clinic.cloc) &&
                Objects.equals(cnumber, clinic.cnumber) && Objects.equals(appointment, clinic.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cname, cloc, cnumber, appointment);
    }

    @Override
    public String toString() {
        return "{" +
                " cname='" + getCname() + "'" +
                ", cloc='" + getCloc() + "'" +
                ", cnumber='" + getCnumber() + "'" +
                ", appointment='" + getAppointment() + "'" +
                "}";
    }
}
