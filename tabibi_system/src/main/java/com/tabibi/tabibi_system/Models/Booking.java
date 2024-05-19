package com.tabibi.tabibi_system.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id ;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "appid", referencedColumnName = "appid", insertable = true, updatable = true)
    private Appointment appointment;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "uid", insertable = true, updatable = true)
    private Patient patient;


    public Booking() {
    }

    public Booking(Appointment appointment, Patient patient) {
       
        this.appointment = appointment;
        this.patient = patient;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Booking id(long id) {
        setId(id);
        return this;
    }

    public Booking appointment(Appointment appointment) {
        setAppointment(appointment);
        return this;
    }

    public Booking patient(Patient patient) {
        setPatient(patient);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Booking)) {
            return false;
        }
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(appointment, booking.appointment) && Objects.equals(patient, booking.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appointment, patient);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", appointment='" + getAppointment() + "'" +
            ", patient='" + getPatient() + "'" +
            "}";
    }
      
    
    
}
