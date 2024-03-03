package com.tabibi.tabibi_system.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "treatment")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treat_id")
    private Long treatId;

    @Column(name = "treat_name")
    private String treatName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "Did", referencedColumnName = "Did", insertable = true, updatable = true)
    private Doctor doctor;


    public Treatment() {
    }

    public Treatment(Long treatId, String treatName, String description, Doctor doctor) {
        this.treatId = treatId;
        this.treatName = treatName;
        this.description = description;
        this.doctor = doctor;
    }

    public Long getTreatId() {
        return this.treatId;
    }

    public void setTreatId(Long treatId) {
        this.treatId = treatId;
    }

    public String getTreatName() {
        return this.treatName;
    }

    public void setTreatName(String treatName) {
        this.treatName = treatName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Treatment treatId(Long treatId) {
        setTreatId(treatId);
        return this;
    }

    public Treatment treatName(String treatName) {
        setTreatName(treatName);
        return this;
    }

    public Treatment description(String description) {
        setDescription(description);
        return this;
    }

    public Treatment doctor(Doctor doctor) {
        setDoctor(doctor);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Treatment)) {
            return false;
        }
        Treatment treatment = (Treatment) o;
        return Objects.equals(treatId, treatment.treatId) && Objects.equals(treatName, treatment.treatName) && Objects.equals(description, treatment.description) && Objects.equals(doctor, treatment.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treatId, treatName, description, doctor);
    }

    @Override
    public String toString() {
        return "{" +
            " treatId='" + getTreatId() + "'" +
            ", treatName='" + getTreatName() + "'" +
            ", description='" + getDescription() + "'" +
            ", doctor='" + getDoctor() + "'" +
            "}";
    }

}

