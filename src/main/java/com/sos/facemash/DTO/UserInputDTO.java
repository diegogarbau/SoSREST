package com.sos.facemash.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserInputDTO {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String mail;
    private int phone;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
