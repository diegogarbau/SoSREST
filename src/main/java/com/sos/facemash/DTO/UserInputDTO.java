package com.sos.facemash.DTO;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sos.facemash.core.validators.PhoneConstraint;

import javax.validation.constraints.NotEmpty;

@JsonPropertyOrder({"userName", "mail", "phone", "name", "lastName"})
public class UserInputDTO {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String mail;
    @PhoneConstraint
    private int phone;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;

    private UserInputDTO() {
    }

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

    public static class Builder {
        private String userName;
        private String mail;
        private int phone;
        private String name;
        private String lastName;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder setPhone(int phone) {
            this.phone = phone;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public UserInputDTO build() {
            UserInputDTO user = new UserInputDTO();
            user.setUserName(userName);
            user.setMail(mail);
            user.setPhone(phone);
            user.setName(name);
            user.setLastName(lastName);
            return user;
        }
    }
}
