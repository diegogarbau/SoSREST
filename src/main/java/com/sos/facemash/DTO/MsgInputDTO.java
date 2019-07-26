package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sos.facemash.entity.User;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@JsonPropertyOrder({"title", "body", "date", "destination"})
public class MsgInputDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    @NotEmpty
    private Date date;
    private User destination;

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

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public static class Builder {
        private String body;
        private Date date;
        private User destination;
        private String title;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }


        public Builder setDestination(User destination) {
            this.destination = destination;
            return this;
        }

        public MsgInputDTO build() {
            MsgInputDTO msgDTO = new MsgInputDTO();
            msgDTO.setTitle(title);
            msgDTO.setBody(body);
            msgDTO.setDate(date);
            msgDTO.setDestination(destination);
            return msgDTO;
        }
    }
}