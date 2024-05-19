package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "patient")
public class Patient extends UserAcc {
    
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Long pid;

    @NotBlank(message = "First Name is required")
    @Column(name = "firstname")
    private String firstname;

    @NotBlank(message = "Last Name is required")
    @Column(name = "lastname")
    private String lastname;

    @NotBlank(message = "Age is required")
    @Column(name = "age")
    private String age;

    @Column(name = "gender")
    private String gender;

    private long test;

    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Number is required")
    @Column(name = "number")
    private String number;

    // Constructors
    public Patient() {
    }

    public Patient( String firstname, String lastname, String age, String gender, String address, String number) {
        // this.pid = pid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.number = number;
    }

    // Getters and Setters
    public Long getPid() {
        return test;
    }

    public void setPid(Long pid) {
        // this.pid = pid;
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
   
    public Patient getPatient() {
        return this;
    }

    
  

    @Override
    public String toString() {
        return "{" +
            " pid='" + getPid() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", age='" + getAge() + "'" +
            ", gender='" + getGender() + "'" +
            ",  + "+
            ", address='" + getAddress() + "'" +
            ", number='" + getNumber() + "'" +
            ", uid ='" + getUid() + "'" +   
            "}"
            
            ;
    }

    // // To String
    // @Override
    // public String toString() {
    //     return "Patient{" +
    //             "pid=" + pid +
    //             ", firstname='" + firstname + '\'' +
    //             ", lastname='" + lastname + '\'' +
    //             ", age='" + age + '\'' +
    //             ", gender='" + gender + '\'' +
    //             ", address='" + address + '\'' +
    //             ", number='" + number + '\'' +
    //             '}';
    // }
}
