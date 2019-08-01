package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotEmpty;

@JsonPropertyOrder({"title", "body", "date", "destination"})
public class MsgInputDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;

    private String destinationId;

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    private void setBody(String body) {
        this.body = body;
    }

    public String getDestinationId() {
        return destinationId;
    }

    private void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public static class Builder {
        private String body;
        private String destinationId;
        private String title;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setDestinationId(String destinationId) {
            this.destinationId = destinationId;
            return this;
        }

        public MsgInputDTO build() {
            MsgInputDTO msgDTO = new MsgInputDTO();
            msgDTO.setTitle(title);
            msgDTO.setBody(body);
            msgDTO.setDestinationId(destinationId);
            return msgDTO;
        }
    }
}