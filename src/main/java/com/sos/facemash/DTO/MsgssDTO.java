package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "Messages")
public class MsgssDTO {
    @JsonProperty(value = "messages")
    private List<MsgSummaryDTO> msgss;

    public void setUsers(List<MsgSummaryDTO> msgss) {

        this.msgss = msgss;
    }

    public MsgssDTO insertAll(List<MsgSummaryDTO> newMessages) {
        msgss.addAll(newMessages);
        return this;
    }
}
