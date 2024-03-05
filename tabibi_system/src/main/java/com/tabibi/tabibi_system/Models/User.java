package com.tabibi.tabibi_system.Models;

import java.lang.annotation.Inherited;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    private String username;
    private String password;
    private String dob;
    private String User_type;


    public User() {
    }

    public User(Long Id, String username, String password, String dob, String User_type) {
        this.Id = Id;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.User_type = User_type;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUser_type() {
        return this.User_type;
    }

    public void setUser_type(String User_type) {
        this.User_type = User_type;
    }

    public User Id(Long Id) {
        setId(Id);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User dob(String dob) {
        setDob(dob);
        return this;
    }

    public User User_type(String User_type) {
        setUser_type(User_type);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(Id, user.Id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(dob, user.dob) && Objects.equals(User_type, user.User_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, username, password, dob, User_type);
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", dob='" + getDob() + "'" +
            ", User_type='" + getUser_type() + "'" +
            "}";
    }

   
}

