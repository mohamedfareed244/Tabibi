package com.tabibi.tabibi_system.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "user_acc")
@Inheritance(strategy = InheritanceType.JOINED)

public class UserAcc {
 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Email
    @NotEmpty(message = "email cannot be empty ")
    @Column(name = "email", nullable = false)
    private String email;
    
    @NotNull
    @Column(name = "pass", nullable = false)
    private String pass;
    
    @ManyToOne
    @JoinColumn(name = "usertype_id", nullable = true)
    private UserTypes usertype;

    @Column(name = "Token")
    private String Token;
    

    
    public UserAcc() {
    }
    
    public UserAcc(int uid, String email, String pass, UserTypes usertype) {
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.usertype = usertype;
    }



   

    @Override
    public int hashCode() {
        return Objects.hash(uid, email, pass, usertype);
    }

    @Override
    public String toString() {
        return "{" +
            " uid='" + getUid() + "'" +
            ", email='" + getEmail() + "'" +
            ", pass='" + getPass() + "'" +
            ", usertype='" + getUsertype() + "'" +
            "}";
    }
    // getters and setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserTypes getUsertype() {
        return usertype;
    }

    public void setUsertype(UserTypes usertype) {
        this.usertype = usertype;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }
    
 
}