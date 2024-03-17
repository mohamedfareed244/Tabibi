package com.tabibi.tabibi_system.Models;

import java.util.Date;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bid")
    private Long bid;

    @ManyToOne
    @JoinColumn(name = "Appid", referencedColumnName = "Appid", insertable = true, updatable = true)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "Pid", referencedColumnName = "Pid", insertable = true, updatable = true)
    private Patient patient;

    @Column(name="amount")
    private String amount;

    @Column(name="date")
    private Date date ;


    public Billing() {
    }

    public Billing(Long bid, Appointment appointment, Patient patient, String amount, Date date) {
        this.bid = bid;
        this.appointment = appointment;
        this.patient = patient;
        this.amount = amount;
        this.date = date;
    }

    public Long getBid() {
        return this.bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Billing bid(Long bid) {
        setBid(bid);
        return this;
    }

    public Billing appointment(Appointment appointment) {
        setAppointment(appointment);
        return this;
    }

    public Billing patient(Patient patient) {
        setPatient(patient);
        return this;
    }

    public Billing amount(String amount) {
        setAmount(amount);
        return this;
    }

    public Billing date(Date date) {
        setDate(date);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Billing)) {
            return false;
        }
        Billing billing = (Billing) o;
        return Objects.equals(bid, billing.bid) && Objects.equals(appointment, billing.appointment) && Objects.equals(patient, billing.patient) && Objects.equals(amount, billing.amount) && Objects.equals(date, billing.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, appointment, patient, amount, date);
    }

    @Override
    public String toString() {
        return "{" +
            " bid='" + getBid() + "'" +
            ", appointment='" + getAppointment() + "'" +
            ", patient='" + getPatient() + "'" +
            ", amount='" + getAmount() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }


}
