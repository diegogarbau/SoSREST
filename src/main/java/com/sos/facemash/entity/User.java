package com.sos.facemash.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String userName;
    private String mail;
    private int phone;
    private String name;
    private String lastName;
    private List<String> friends;
    private List<Long> messages;

    private User() {
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    private void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhone() {
        return phone;
    }

    private void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getFriends() {
        return friends;
    }

    private void setFriends(List<String> friends) {
        this.friends = friends;
    }

    private List<Long> getMessages() {
        return messages;
    }

    private void setMessages(List<Long> messages) {
        this.messages = messages;
    }

    public User updateUser (User newUser){
        this.setMail(newUser.getMail());
        this.setPhone(newUser.getPhone());
        this.setName(newUser.getName());
        this.setLastName(newUser.getLastName());
        this.setFriends(newUser.getFriends());
        this.setMessages(newUser.getMessages());
        return this;
    }


    public static class Builder {
        private String userName;
        private String mail;
        private int phone;
        private String name;
        private String lastName;
        private List<String> friends;
        private List<Long> messages;

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

        public Builder setFriends(List<String> friends) {
            this.friends = friends;
            return this;
        }

        public Builder setMessages(List<Long> messages) {
            this.messages = messages;
            return this;
        }

        public User build() {
            User user = new User();
            user.setUserName(userName);
            user.setMail(mail);
            user.setPhone(phone);
            user.setName(name);
            user.setLastName(lastName);
            user.setFriends(friends);
            user.setMessages(messages);
            return user;
        }
    }

}
