package com.sos.facemash.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "Messages")
public class MsgssDTO {
    @JsonProperty(value = "messages")
    private List<MsgSummaryDTO> msgss;

    public MsgssDTO(List<MsgSummaryDTO> msgss) {
        this.msgss = msgss;
    }

    public List<MsgSummaryDTO> getMsgss() {
        return msgss;
    }
}
