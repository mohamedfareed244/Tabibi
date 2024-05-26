package com.tabibi.tabibi_system.Models;

public class Feedback {

    private int id;

    private String comments;

    private int rating;

    private String mail;


    public Feedback() {
    }

    public Feedback(int id, String comments, int rating, String mail) {
        this.id = id;
        this.comments = comments;
        this.rating = rating;
        this.mail = mail;
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



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", comments='" + getComments() + "'" +
            ", rating='" + getRating() + "'" +
            ", mail='" + getMail() + "'" +
            "}";
    }
   
}

