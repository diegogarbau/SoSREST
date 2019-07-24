package com.sos.facemash.DTO;

import com.sos.facemash.entity.User;

import java.util.Date;

public class MsgDetailDTO {
    private String title;
    private String body;
    private Date date;
    private User Owner;
    private User Destination;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User Owner) {
        this.Owner = Owner;
    }

    public User getDestination() {
        return Destination;
    }

    public void setDestination(User Destination) {
        this.Destination = Destination;
    }

    public static class Builder {

        private String title;
        private String body;
        private Date date;
        private User Owner;
        private User Destination;

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setTitle(String title) {
            this.body = title;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setOwner(User Owner) {
            this.Owner = Owner;
            return this;
        }

        public Builder setDestination(User Destination) {
            this.Destination = Destination;
            return this;
        }

        public MsgDetailDTO build() {
            MsgDetailDTO msgDetailDTO = new MsgDetailDTO();

            msgDetailDTO.setTitle(title);
            msgDetailDTO.setBody(body);
            msgDetailDTO.setDate(date);
            msgDetailDTO.setDestination(Destination);
            msgDetailDTO.setOwner(Owner);
            return msgDetailDTO;
        }
    }
}
