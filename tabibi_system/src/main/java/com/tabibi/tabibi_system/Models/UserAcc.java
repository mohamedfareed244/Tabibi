package com.tabibi.tabibi_system.Models;
import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "user_acc")
public class UserAcc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;


    public UserAcc() {
    }

    public UserAcc(Long uid, String username, String password, String role) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getUid() {
        return this.uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserAcc uid(Long uid) {
        setUid(uid);
        return this;
    }

    public UserAcc username(String username) {
        setUsername(username);
        return this;
    }

    public UserAcc password(String password) {
        setPassword(password);
        return this;
    }

    public UserAcc role(String role) {
        setRole(role);
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
        return Objects.equals(uid, userAcc.uid) && Objects.equals(username, userAcc.username) && Objects.equals(password, userAcc.password) && Objects.equals(role, userAcc.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username, password, role);
    }

    @Override
    public String toString() {
        return "{" +
            " uid='" + getUid() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
    
}

