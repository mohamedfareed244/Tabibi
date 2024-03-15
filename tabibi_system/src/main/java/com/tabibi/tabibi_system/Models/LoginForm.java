package com.tabibi.tabibi_system.Models;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Objects;

public class LoginForm {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    


    @NotBlank(message = "Password is required")
    private String pass;

    public LoginForm() {
    }

    public LoginForm(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LoginForm email(String email) {
        setEmail(email);
        return this;
    }

    public LoginForm pass(String pass) {
        setPass(pass);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LoginForm)) {
            return false;
        }
        LoginForm loginForm = (LoginForm) o;
        return Objects.equals(email, loginForm.email) && Objects.equals(pass, loginForm.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, pass);
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", pass='" + getPass() + "'" +
            "}";
    }

    // Getters and setters
    // ...
}