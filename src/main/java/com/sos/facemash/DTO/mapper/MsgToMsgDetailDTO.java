package com.sos.facemash.DTO.mapper;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.entity.Msg;

public class MsgToMsgDetailDTO {
    private MsgToMsgDetailDTO() {
    }

    public static MsgDetailDTO map(Msg msg) {
        return new MsgDetailDTO.Builder()
                .setTitle(msg.getTitle())
                .setBody(msg.getBody())
                .setDate(msg.getDate())
                .setDestination(msg.getDestination())
                .setOwner(msg.getOwner())
                .build();
    }
}
