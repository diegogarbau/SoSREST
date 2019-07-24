package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sos.facemash.entity.User;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@JsonPropertyOrder({"title", "year", "genre", "cast"})
public class MsgInputDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    @NotEmpty
    private Date date;
    @NotEmpty
    private User owner;
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

    public User getIdOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getIdDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public static class Builder {
        private String body;
        private Date date;
        private User owner;
        private User destination;

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setIdOwner(User idOwner) {
            this.owner = owner;
            return this;
        }

        public Builder setDestination(User Destination) {
            this.destination = Destination;
            return this;
        }

        public MsgDetailDTO build() {
            MsgDetailDTO msgDTO = new MsgDetailDTO();
            msgDTO.setBody(body);
            msgDTO.setDate(date);
            msgDTO.setDestination(destination);
            msgDTO.setOwner(owner);
            return msgDTO;
        }
    }
}