package com.sos.facemash.DTO;

import com.sos.facemash.entity.User;

import java.util.Date;

public class MsgDetailDTO {
    private String title;
    private String body;
    private Date date;
    private String owner;
    private String destination;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public static class Builder {

        private String title;
        private String body;
        private Date date;
        private String owner;
        private String destination;


        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public MsgDetailDTO build() {
            MsgDetailDTO msgDetailDTO = new MsgDetailDTO();

            msgDetailDTO.setTitle(title);
            msgDetailDTO.setBody(body);
            msgDetailDTO.setDate(date);
            msgDetailDTO.setDestination(destination);
            msgDetailDTO.setOwner(owner);
            return msgDetailDTO;
        }
    }
}
