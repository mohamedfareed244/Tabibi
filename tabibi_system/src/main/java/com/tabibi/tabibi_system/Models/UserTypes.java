package com.tabibi.tabibi_system.Models;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "usertypes")
public class UserTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utid;

    @Column(name = "name", nullable = false)
    private String name;
@ManyToMany
private List<Pages>pages=new ArrayList<>();

    public UserTypes() {

    }

    public UserTypes(Long utid, String name) {
        this.utid = utid;
        this.name = name;
    }
    public UserTypes(long id ) {
        this.utid=id;

    }
    

  public Long getUtid(){
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

   
    

    
}

