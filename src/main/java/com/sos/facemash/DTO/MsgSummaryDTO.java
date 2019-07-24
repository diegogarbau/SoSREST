package com.sos.facemash.DTO;

import com.sos.facemash.entity.User;

import java.util.Date;

public class MsgSummaryDTO {
    private String title;
    private Date date;
    private User owner;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public static class Builder {

        private String title;
        private Date date;
        private User owner;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public MsgSummaryDTO build() {
            MsgSummaryDTO msgDTO = new MsgSummaryDTO();

            msgDTO.setTitle(title);
            msgDTO.setDate(date);
            msgDTO.setOwner(owner);
            return msgDTO;
        }
    }
}
