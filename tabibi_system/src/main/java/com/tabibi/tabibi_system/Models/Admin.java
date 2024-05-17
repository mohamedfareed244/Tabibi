package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admin")
public class Admin extends UserAcc {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column(name = "name")
    private String name;

    // @ManyToOne
    // @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = true, updatable = true)
    // private UserAcc userAcc;


    private long test;





    


    public Admin() {
    }

    public Admin( String name ) {
        // this.aid = aid;
        this.name = name;
        // this.userAcc = userAcc;
    }

    public Long getAid() {
        return this.test;
    }

    public void setAid(Long aid) {
        // this.aid = aid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public UserAcc getUserAcc() {
    //     return this.userAcc;
    // }

    // public void setUserAcc(UserAcc userAcc) {
    //     this.userAcc = userAcc;
    // }

    public Admin aid(Long aid) {
        setAid(aid);
        return this;
    }

    public Admin name(String name) {
        setName(name);
        return this;
    }

    // public Admin userAcc(UserAcc userAcc) {
    //     setUserAcc(userAcc);
    //     return this;
    // }

    // @Override
    // public boolean equals(Object o) {
    //     if (o == this)
    //         return true;
    //     if (!(o instanceof Admin)) {
    //         return false;
    //     }
    //     Admin admin = (Admin) o;
    //     return Objects.equals(aid, admin.aid) && Objects.equals(name, admin.name) && Objects.equals(userAcc, admin.userAcc);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(aid, name, userAcc);
    // }

    // @Override
    // public String toString() {
    //     return "{" +
    //         " aid='" + getAid() + "'" +
    //         ", name='" + getName() + "'" +
    //         ", userAcc='" + getUserAcc() + "'" +
    //         "}";
    // }
  
}

