package com.tabibi.tabibi_system.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Feedback {

    private int id;

    private String comments;

    private int rating;

    private String mail;

    private LocalDate date ;

    // private String formattedDate ;



    public Feedback() {
    }

    public Feedback(int id, String comments, int rating, String mail, LocalDate date) {
        this.id = id;
        this.comments = comments;
        this.rating = rating;
        this.mail = mail;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Feedback id(int id) {
        setId(id);
        return this;
    }

    public Feedback comments(String comments) {
        setComments(comments);
        return this;
    }

    public Feedback rating(int rating) {
        setRating(rating);
        return this;
    }

    public Feedback mail(String mail) {
        setMail(mail);
        return this;
    }

    public Feedback date(LocalDate date) {
        setDate(date);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Feedback)) {
            return false;
        }
        Feedback feedback = (Feedback) o;
        return id == feedback.id && Objects.equals(comments, feedback.comments) && rating == feedback.rating && Objects.equals(mail, feedback.mail) && Objects.equals(date, feedback.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comments, rating, mail, date);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", comments='" + getComments() + "'" +
            ", rating='" + getRating() + "'" +
            ", mail='" + getMail() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

    // public  void formatDate12() {
   
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
    //     this.formattedDate = this.date.format(formatter);
  
    // }

    // public String getFormattedDate() {
    //     return formattedDate;
    // }

    // public void setFormattedDate(String formattedDate) {
    //     this.formattedDate = formattedDate;
    // }

   
}

