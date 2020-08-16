package com.sept.bookingsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, message = "Username must be at least two characters long")
    @NotBlank(message = "Username cannot be blank")
    private String userName;
    @Size(min = 2, message = "Password must be at least two characters long")
    @NotBlank(message = "Password cannot be blank")
    private String passWord;
    @Size(min = 2, message = "First Name must be at least two characters long")
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @Size(min = 2, message = "Last Name must be at least two characters long")
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;
    private boolean isActive = true;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Roles cannot be blank")
    private String roles;
    @JsonFormat(pattern = "dd-MM-yyy")
    private Date created_on;
    @JsonFormat(pattern = "dd-MM-yyy")
    private Date updated_on;

    @PrePersist
    private void setCreated_on() {
        this.created_on = new Date();
    }
    @PreUpdate
    private void setUpdated_on() {
        this.updated_on = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
