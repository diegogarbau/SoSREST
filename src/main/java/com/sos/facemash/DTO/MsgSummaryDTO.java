package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@JsonRootName(value = "message")
@JsonPropertyOrder({"title", "date",})
public class MsgSummaryDTO {
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setDate(Date date) {
        this.date = date;
    }


    public static class Builder {

        private String title;
        private Date date;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public MsgSummaryDTO build() {
            MsgSummaryDTO msgDTO = new MsgSummaryDTO();

            msgDTO.setTitle(title);
            msgDTO.setDate(date);
            return msgDTO;
        }
    }
}
