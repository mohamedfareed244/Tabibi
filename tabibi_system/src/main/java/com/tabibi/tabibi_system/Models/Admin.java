package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admin")
public class Admin extends UserAcc {


    @Column(name = "name")
    private String name;


    public Admin() {
    }

    public Admin( String name ) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Admin name(String name) {
        setName(name);
        return this;
    }
  
}

