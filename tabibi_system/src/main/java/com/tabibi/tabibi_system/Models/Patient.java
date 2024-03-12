package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity

@Table(name = "patient")
public class Patient {
    
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pid")
    private Long pid;
    
    @Column(name = "firstname")
    private String firstname;
    
    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "age")
    private String age;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "number")
    private String number;
    
    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = true, updatable = true)
    private UserAcc userAcc;
    public Patient() {
        }

        public Patient(Long pid, String firstname, String lastname, String age, String gender, String address, String number, UserAcc userAcc) {
            this.pid = pid;
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
            this.gender = gender;
            this.address = address;
            this.number = number;
            this.userAcc = userAcc;
        }

       
    
        public Long getPid() {
            return pid;
        }
    
        public void setPid(Long pid) {
            this.pid = pid;
        }
    
        public String getFirstname() {
            return firstname;
        }
    
        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }
    
        public String getLastname() {
            return lastname;
        }
    
        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
    
        public String getAge() {
            return age;
        }
    
        public void setAge(String age) {
            this.age = age;
        }
    
        public String getGender() {
            return gender;
        }
    
        public void setGender(String gender) {
            this.gender = gender;
        }
    
        public String getAddress() {
            return address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        public String getNumber() {
            return number;
        }
    
        public void setNumber(String number) {
            this.number = number;
        }
    
        public UserAcc getUserAcc() {
            return userAcc;
        }
    
        public void setUserAcc(UserAcc userAcc) {
            this.userAcc = userAcc;
        }
        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Patient)) {
                return false;
            }
            Patient patient = (Patient) o;
            return Objects.equals(pid, patient.pid) && Objects.equals(firstname, patient.firstname) && Objects.equals(lastname, patient.lastname) && Objects.equals(age, patient.age) && Objects.equals(gender, patient.gender) && Objects.equals(address, patient.address) && Objects.equals(number, patient.number) && Objects.equals(userAcc, patient.userAcc);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pid, firstname, lastname, age, gender, address, number, userAcc);
        }

        @Override
        public String toString() {
            return "{" +
                " pid='" + getPid() + "'" +
                ", firstname='" + getFirstname() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", age='" + getAge() + "'" +
                ", gender='" + getGender() + "'" +
                ", address='" + getAddress() + "'" +
                ", number='" + getNumber() + "'" +
                ", userAcc='" + getUserAcc() + "'" +
                "}";
        }
    
    }

