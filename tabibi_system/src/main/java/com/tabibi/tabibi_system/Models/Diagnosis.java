package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "diagnosis")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id")
    private Long diagnosisId;

    @Column(name = "diagnosis_name")
    private String diagnosisName;
    
    @Column(name = "treatment")
    private String treatment;
  

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

    public Diagnosis(Long diagnosisId, String diagnosisName, String treatment, UserAcc userAcc, UserAcc user) {
        this.diagnosisId = diagnosisId;
        this.diagnosisName = diagnosisName;
        this.treatment = treatment;
        this.userAcc = userAcc;
        this.user = user;
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

    public String getTreatment() {
        return this.treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public UserAcc getUserAcc() {
        return this.userAcc;
    }

    public void setUserAcc(UserAcc userAcc) {
        this.userAcc = userAcc;
    }

    public String getDoctorName() {
        return doctorName;
    }
    
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public UserAcc getUser() {
        return this.user;
    }

    public void setUser(UserAcc user) {
        this.user = user;
    }

    public Diagnosis diagnosisId(Long diagnosisId) {
        setDiagnosisId(diagnosisId);
        return this;
    }

    public Diagnosis diagnosisName(String diagnosisName) {
        setDiagnosisName(diagnosisName);
        return this;
    }

    public Diagnosis treatment(String treatment) {
        setTreatment(treatment);
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Diagnosis)) {
            return false;
        }
        Diagnosis diagnosis = (Diagnosis) o;
        return Objects.equals(diagnosisId, diagnosis.diagnosisId) && Objects.equals(diagnosisName, diagnosis.diagnosisName) && Objects.equals(treatment, diagnosis.treatment) && Objects.equals(userAcc, diagnosis.userAcc) && Objects.equals(user, diagnosis.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diagnosisId, diagnosisName, treatment, userAcc, user);
    }

    @Override
    public String toString() {
        return "{" +
            " diagnosisId='" + getDiagnosisId() + "'" +
            ", diagnosisName='" + getDiagnosisName() + "'" +
            ", treatment='" + getTreatment() + "'" +
            ", userAcc='" + getUserAcc() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }


    
    
    
    
}
