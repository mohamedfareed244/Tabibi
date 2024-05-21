package com.tabibi.tabibi_system.Models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="userlog")
public class UserLog {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int Id;
    
    private int userId;
    
    private Date logDate;

    public UserLog() {
    }

    public UserLog(int Id, int UserId, Date Date) {
        this.Id = Id;
        this.userId = UserId;
        this.logDate = Date;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int UserId) {
        this.userId = UserId;
    }

    public Date getDate() {
        return this.logDate;
    }

    public void setDate(Date Date) {
        this.logDate = Date;
    }

    public UserLog Id(int Id) {
        setId(Id);
        return this;
    }

    public UserLog UserId(int UserId) {
        setUserId(UserId);
        return this;
    }

    public UserLog Date(Date Date) {
        setDate(Date);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserLog)) {
            return false;
        }
        UserLog userLog = (UserLog) o;
        return Id == userLog.Id && userId == userLog.userId && Objects.equals(logDate, userLog.logDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, userId, logDate);
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", UserId='" + getUserId() + "'" +
            ", Date='" + getDate() + "'" +
            "}";
    }

    
}
