package com.example.login.Model;

import javax.validation.constraints.NotBlank;

public class SignupModel {

    private String name;
    @NotBlank(message = "Username cannot be empty!")
    private String userName;
    private String email;
    @NotBlank(message = "Password cannot be empty!")
    private String password;
    @NotBlank(message = "RePassword cannot be empty!")
    private String rePassword;

    public SignupModel() {
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
