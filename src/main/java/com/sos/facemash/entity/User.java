package com.sos.facemash.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_Name")
    private String userName;
    @Column(name = "mail")
    private String mail;
    @Column(name = "phone")
    private int phone;
    @Column(name = "name")
    private String name;
    @Column(name = "last_Name")
    private String lastName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "users.user_Name"),
            inverseJoinColumns = @JoinColumn(name = "users.user_Name"))
    private List<User> friends;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "messages",
            joinColumns = @JoinColumn(name = "users.user_Name"),
            inverseJoinColumns = @JoinColumn(name = "messages.id"))
    private List<Msg> messages;

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

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Msg> getMessages() {
        return messages;
    }

    public void setMessages(List<Msg> messages) {
        this.messages = messages;
    }

    public User updateUser (User newUser){
        this.setMail(newUser.getMail());
        this.setPhone(newUser.getPhone());
        this.setName(newUser.getName());
        this.setLastname(newUser.getLastName());
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
        private List<User> friends;
        private List<Msg> messages;

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

        public Builder setFriends(List<User> friends) {
            this.friends = friends;
            return this;
        }

        public Builder setMesagges(List<Msg> messages) {
            this.messages = messages;
            return this;
        }

        public User build() {
            User user = new User();
            user.setUserName(userName);
            user.setMail(mail);
            user.setPhone(phone);
            user.setName(name);
            user.setLastname(lastName);
            user.setFriends(friends);
            user.setMessages(messages);
            return user;
        }
    }

}
