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


    public Diagnosis() {
    }

    public Diagnosis(Long diagnosisId, String diagnosisName, String treatment, UserAcc userAcc) {
        this.diagnosisId = diagnosisId;
        this.diagnosisName = diagnosisName;
        this.treatment = treatment;
        this.userAcc = userAcc;
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


    
    
    
    
}
