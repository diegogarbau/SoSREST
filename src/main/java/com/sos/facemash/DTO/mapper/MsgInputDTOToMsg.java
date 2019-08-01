package com.sos.facemash.DTO.mapper;

import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.entity.Msg;

import java.sql.Timestamp;
import java.util.Date;

public class MsgInputDTOToMsg {
    private MsgInputDTOToMsg() {
    }

    public static Msg map(MsgInputDTO msgDTO) {
        return new Msg.Builder()
                .setTitle(msgDTO.getTitle())
                .setBody(msgDTO.getBody())
                .setDate(new Timestamp(new Date().getTime()))
                .build();
    }
}
