package com.sos.facemash.DTO.mapper;

import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.entity.Msg;

public class MsgInputDTOToMsg {
    private MsgInputDTOToMsg() {
    }

    public static Msg map(MsgInputDTO msgDTO) {
        return new Msg.Builder()
                .setTitle(msgDTO.getTitle())
                .setBody(msgDTO.getBody())
                .setDate(msgDTO.getDate())
                .setDestination(msgDTO.getIdDestination())
                .setOwner(msgDTO.getIdOwner())
                .build();
    }
}
