package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "diagnosis")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id", nullable = false)
    private Long diagnosisId;

    @Column(name = "diagnosis_name", nullable = false)
    private String diagnosisName;
    
    @Column(name = "notes", nullable = true)
    private String notes;

    @Column(name = "medicineName", nullable = true)
    private String medicineName;
  

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = true, updatable = true)
    private UserAcc userAcc;

    @ManyToOne
    @JoinColumn(name = "did", referencedColumnName = "uid", insertable = true, updatable = true)
    private UserAcc user;

    @Transient
    private String doctorName;




   

    public Diagnosis() {
    }

    public Diagnosis(Long diagnosisId, String diagnosisName, String notes, String medicineName, UserAcc userAcc, UserAcc user, String doctorName) {
        this.diagnosisId = diagnosisId;
        this.diagnosisName = diagnosisName;
        this.notes = notes;
        this.medicineName = medicineName;
        this.userAcc = userAcc;
        this.user = user;
        this.doctorName = doctorName;
    }

    public Long getDiagnosisId() {
        return this.diagnosisId;
    }

    public void setDiagnosisId(Long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getDiagnosisName() {
        return this.diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMedicineName() {
        return this.medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public UserAcc getUserAcc() {
        return this.userAcc;
    }

    public void setUserAcc(UserAcc userAcc) {
        this.userAcc = userAcc;
    }

    public UserAcc getUser() {
        return this.user;
    }

    public void setUser(UserAcc user) {
        this.user = user;
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Diagnosis diagnosisId(Long diagnosisId) {
        setDiagnosisId(diagnosisId);
        return this;
    }

    public Diagnosis diagnosisName(String diagnosisName) {
        setDiagnosisName(diagnosisName);
        return this;
    }

    public Diagnosis notes(String notes) {
        setNotes(notes);
        return this;
    }

    public Diagnosis medicineName(String medicineName) {
        setMedicineName(medicineName);
        return this;
    }

    public Diagnosis userAcc(UserAcc userAcc) {
        setUserAcc(userAcc);
        return this;
    }

    public Diagnosis user(UserAcc user) {
        setUser(user);
        return this;
    }

    public Diagnosis doctorName(String doctorName) {
        setDoctorName(doctorName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Diagnosis)) {
            return false;
        }
        Diagnosis diagnosis = (Diagnosis) o;
        return Objects.equals(diagnosisId, diagnosis.diagnosisId) && Objects.equals(diagnosisName, diagnosis.diagnosisName) && Objects.equals(notes, diagnosis.notes) && Objects.equals(medicineName, diagnosis.medicineName) && Objects.equals(userAcc, diagnosis.userAcc) && Objects.equals(user, diagnosis.user) && Objects.equals(doctorName, diagnosis.doctorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diagnosisId, diagnosisName, notes, medicineName, userAcc, user, doctorName);
    }

    @Override
    public String toString() {
        return "{" +
            " diagnosisId='" + getDiagnosisId() + "'" +
            ", diagnosisName='" + getDiagnosisName() + "'" +
            ", notes='" + getNotes() + "'" +
            ", medicineName='" + getMedicineName() + "'" +
            ", userAcc='" + getUserAcc() + "'" +
            ", user='" + getUser() + "'" +
            ", doctorName='" + getDoctorName() + "'" +
            "}";
    }


    
    
    
    
}
