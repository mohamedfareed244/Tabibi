
package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "pages")
public class pages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pgid")
    private Long pgid;

    @Column(name = "name")
    private String name;

    @Column(name = "linkaddress")
    private String linkaddress;

    @Column(name = "icons")
    private String icons;

    @Column(name = "class")
    private String classX;

    
}

