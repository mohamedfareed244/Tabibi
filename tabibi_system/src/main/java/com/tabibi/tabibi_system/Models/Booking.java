package com.tabibi.tabibi_system.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id ;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "appid", referencedColumnName = "appid", insertable = true, updatable = true)
    private Appointment appointment;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "uid", insertable = true, updatable = true)
    private Patient patient;

      
    
    
}
