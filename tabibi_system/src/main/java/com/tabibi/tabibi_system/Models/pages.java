
package com.tabibi.tabibi_system.Models;

import jakarta.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "pages")
public class Pages {

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

    

    public Pages() {
    }

    public Pages(Long pgid, String name, String linkaddress, String icons, String classX) {
        this.pgid = pgid;
        this.name = name;
        this.linkaddress = linkaddress;
        this.icons = icons;
        this.classX = classX;
    }

    public Long getPgid() {
        return this.pgid;
    }

    public void setPgid(Long pgid) {
        this.pgid = pgid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkaddress() {
        return this.linkaddress;
    }

    public void setLinkaddress(String linkaddress) {
        this.linkaddress = linkaddress;
    }

    public String getIcons() {
        return this.icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getClassX() {
        return this.classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public Pages pgid(Long pgid) {
        setPgid(pgid);
        return this;
    }

    public Pages name(String name) {
        setName(name);
        return this;
    }

    public Pages linkaddress(String linkaddress) {
        setLinkaddress(linkaddress);
        return this;
    }

    public Pages icons(String icons) {
        setIcons(icons);
        return this;
    }

    public Pages classX(String classX) {
        setClassX(classX);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pages)) {
            return false;
        }
        Pages pages = (Pages) o;
        return Objects.equals(pgid, pages.pgid) && Objects.equals(name, pages.name) && Objects.equals(linkaddress, pages.linkaddress) && Objects.equals(icons, pages.icons) && Objects.equals(classX, pages.classX);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pgid, name, linkaddress, icons, classX);
    }

    @Override
    public String toString() {
        return "{" +
            " pgid='" + getPgid() + "'" +
            ", name='" + getName() + "'" +
            ", linkaddress='" + getLinkaddress() + "'" +
            ", icons='" + getIcons() + "'" +
            ", classX='" + getClassX() + "'" +
            "}";
    }

}
