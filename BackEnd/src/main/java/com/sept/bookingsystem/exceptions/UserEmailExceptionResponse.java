package com.sept.bookingsystem.exceptions;

public class UserEmailExceptionResponse {

    private String userEmail;

    public UserEmailExceptionResponse(String projectIdentifier){
        this.userEmail = projectIdentifier;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
