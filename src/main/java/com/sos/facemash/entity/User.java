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
    //========

    @ManyToMany
    @JoinTable(name="friends",
            joinColumns=@JoinColumn(name="userName"),
            inverseJoinColumns=@JoinColumn(name="friendId"))
    private List<User> friends;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="friends",
            joinColumns=@JoinColumn(name="friendId"),
            inverseJoinColumns=@JoinColumn(name="userName"))
    private List<User> friendOf;
    //========












    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "messagesSent",
            joinColumns = @JoinColumn(name = "users_userName"),
            inverseJoinColumns = @JoinColumn(name = "messages_id"))
    private List<Msg> messagesSent;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "messagesReceived",
            joinColumns = @JoinColumn(name = "users_userName"),
            inverseJoinColumns = @JoinColumn(name = "messages_id"))
    private List<Msg> messagesReceived;


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

    public List<User> getFriends() {
        return friends;
    }

    private void setFriends(List<User> friends) {
        this.friends = friends;
    }

    private List<Msg> getMessagesSent() {
        return messagesSent;
    }

    private void setMessagesSent(List<Msg> messagesSent) {
        this.messagesSent = messagesSent;
    }
    public List<Msg> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<Msg> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public User updateUser(User newUser) {
        this.setMail(newUser.getMail());
        this.setPhone(newUser.getPhone());
        this.setName(newUser.getName());
        this.setLastName(newUser.getLastName());
        this.setFriends(newUser.getFriends());
        this.setMessagesSent(newUser.getMessagesSent());
        return this;
    }


    public static class Builder {
        private String userName;
        private String mail;
        private int phone;
        private String name;
        private String lastName;
        private List<User> friends;
        private List<Msg> messagesSent;
        private List<Msg> messagesReceived;


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

        public Builder setMessagesSent(List<Msg> messagesSent) {
            this.messagesReceived = messagesSent;
            return this;
        }

        public Builder setMessagesReceived(List<Msg> messagesReceived) {
            this.messagesReceived = messagesReceived;
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
            user.setMessagesSent(messagesSent);
            user.setMessagesReceived(messagesReceived);
            return user;
        }
    }

}
