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
@Table(name = "workplaces")
public class Workplaces {
@Id
@Column(name="id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@NotNull
@ManyToOne
@JoinColumn(name = "cid" , referencedColumnName="uid" , insertable=true , updatable = true  )
    private Clinic clinic;
@NotNull
@ManyToOne
@JoinColumn(name = "did" , referencedColumnName="uid" , insertable=true , updatable = true  )
    private Doctor doctor ;

    public Workplaces() {
    }

    public Workplaces(long id, Clinic clinic, Doctor doctor) {
        this.id = id;
        this.clinic = clinic;
        this.doctor = doctor;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Workplaces id(long id) {
        setId(id);
        return this;
    }

    public Workplaces clinic(Clinic clinic) {
        setClinic(clinic);
        return this;
    }

    public Workplaces doctor(Doctor doctor) {
        setDoctor(doctor);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Workplaces)) {
            return false;
        }
        Workplaces workplaces = (Workplaces) o;
        return id == workplaces.id && Objects.equals(clinic, workplaces.clinic) && Objects.equals(doctor, workplaces.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clinic, doctor);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", clinic='" + getClinic() + "'" +
            ", doctor='" + getDoctor() + "'" +
            "}";
    }


    
    
}
