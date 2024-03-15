package com.tabibi.tabibi_system.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "user_acc")

public class UserAcc {
 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    
 @NotNull
    @Column(name = "email", nullable = false)
    private String email;
    
    @NotNull
    @Column(name = "pass", nullable = false)
    private String pass;
    
    @ManyToOne
    @JoinColumn(name = "usertype_id", nullable = true)
    private UserTypes usertype;
    
    @Column(name = "image", nullable = true)
    private String image;
    
    public UserAcc() {
    }
    
    public UserAcc(int uid, String email, String pass, UserTypes usertype, String image) {
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.usertype = usertype;
        this.image = image;
    }
    public UserAcc image(String image) {
        setImage(image);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserAcc)) {
            return false;
        }
        UserAcc userAcc = (UserAcc) o;
        return Objects.equals(uid, userAcc.uid) && Objects.equals(email, userAcc.email) && Objects.equals(pass, userAcc.pass) && Objects.equals(usertype, userAcc.usertype) && Objects.equals(image, userAcc.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, email, pass, usertype, image);
    }

    @Override
    public String toString() {
        return "{" +
            " uid='" + getUid() + "'" +
            ", email='" + getEmail() + "'" +
            ", pass='" + getPass() + "'" +
            ", usertype='" + getUsertype() + "'" +
            ", image='" + getImage() + "'" +
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}