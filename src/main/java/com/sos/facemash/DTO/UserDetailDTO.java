package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
@JsonRootName(value = "User ")
@JsonPropertyOrder({"userName", "mail", "phone", "name", "lastName", "friends", "messagesSent", "messagesReceived"})
public class UserDetailDTO {
    private String userName;
    private String mail;
    private int phone;
    private String name;
    private String lastName;
    private List<UserSummaryDTO> friends;
    private List<MsgSummaryDTO> messagesSent;
    private List<MsgSummaryDTO> messagesReceived;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
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

    public List<UserSummaryDTO> getFriends() {
        return friends;
    }

    private void setFriends(List<UserSummaryDTO> friends) {
        this.friends = friends;
    }

    public List<MsgSummaryDTO> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<MsgSummaryDTO> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public List<MsgSummaryDTO> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<MsgSummaryDTO> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public static class Builder {
        private String userName;
        private String mail;
        private int phone;
        private String name;
        private String lastName;
        private List<UserSummaryDTO> friends;
        private List<MsgSummaryDTO> messagesSent;
        private List<MsgSummaryDTO> messagesReceived;

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

        public Builder setFriends(List<UserSummaryDTO> friends) {
            this.friends = friends;
            return this;
        }

        public Builder setMessagesReceived(List<MsgSummaryDTO> messagesReceived) {
            this.messagesReceived = messagesReceived;
            return this;
        }

        public Builder setMessagesSent(List<MsgSummaryDTO> messagesSent) {
            this.messagesSent = messagesSent;
            return this;
        }

        public UserDetailDTO build() {
            UserDetailDTO userDetailDTO = new UserDetailDTO();
            userDetailDTO.setUserName(userName);
            userDetailDTO.setMail(mail);
            userDetailDTO.setPhone(phone);
            userDetailDTO.setName(name);
            userDetailDTO.setLastName(lastName);
            userDetailDTO.setFriends(friends);
            userDetailDTO.setMessagesSent(messagesSent);
            userDetailDTO.setMessagesReceived(messagesReceived);
            return userDetailDTO;
        }
    }

}
