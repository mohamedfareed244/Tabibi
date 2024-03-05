package com.tabibi.tabibi_system.Models;
import jakarta.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "usertypes")
public class UserTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utid;

    @Column(name = "name", nullable = false)
    private String name;


    public UserTypes() {
    }

    public UserTypes(Long utid, String name) {
        this.utid = utid;
        this.name = name;
    }

    public Long getUtid() {
        return this.utid;
    }

    public void setUtid(Long utid) {
        this.utid = utid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserTypes utid(Long utid) {
        setUtid(utid);
        return this;
    }

    public UserTypes name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserTypes)) {
            return false;
        }
        UserTypes userTypes = (UserTypes) o;
        return Objects.equals(utid, userTypes.utid) && Objects.equals(name, userTypes.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utid, name);
    }

    @Override
    public String toString() {
        return "{" +
            " utid='" + getUtid() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
    

    
}

