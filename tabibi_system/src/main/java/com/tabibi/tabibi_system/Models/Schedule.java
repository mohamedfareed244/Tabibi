package com.tabibi.tabibi_system.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sid")
    private Long sid;

    @ManyToOne
    @JoinColumn(name = "Did", referencedColumnName = "Did", insertable = true, updatable = true)
    private Doctor doctor;

    @Column(name = "day")
    private String day;

    @Column(name = "starttime")
    private String starttime;

    @Column(name = "endtime")
    private String endtime;

   
}

