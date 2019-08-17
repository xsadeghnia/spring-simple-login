package com.example.login.Model;

import javax.validation.constraints.NotBlank;

public class LoginModel {
    @NotBlank(message = "Username cannot be empty!")
    private String userName;
    @NotBlank(message = "Password cannot be empty!")
    private String password;

    public LoginModel() {
    }

    public LoginModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
