package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@JsonRootName(value = "message")
@JsonPropertyOrder({"title", "owner", "date", "destination"})
public class MsgSummaryDTO {
    private String title;
    private String owner;
    private String destination;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

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

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public static class Builder {

        private String title;
        private Date date;
        private String owner;
        private String destination;


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

        public MsgSummaryDTO build() {
            MsgSummaryDTO msgDTO = new MsgSummaryDTO();

            msgDTO.setTitle(title);
            msgDTO.setDate(date);
            msgDTO.setOwner(owner);
            msgDTO.setDestination(destination);
            return msgDTO;
        }
    }
}
