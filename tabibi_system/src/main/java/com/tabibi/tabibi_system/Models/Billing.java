package com.tabibi.tabibi_system.Models;

import java.util.Date;

import jakarta.persistence.*;
@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bid")
    private Long bid;

    @ManyToOne
    @JoinColumn(name = "Appid", referencedColumnName = "Appid", insertable = true, updatable = true)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "Pid", referencedColumnName = "Pid", insertable = true, updatable = true)
    private Patient patient;

    @Column(name="amount")
    private String amount;

    @Column(name="date")
    private Date date ;



}
