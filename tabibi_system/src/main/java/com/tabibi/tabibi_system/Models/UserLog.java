package com.tabibi.tabibi_system.Models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userlog")
public class UserLog {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int Id;
    
    private int UserId;
    private Date Date;

    
}
