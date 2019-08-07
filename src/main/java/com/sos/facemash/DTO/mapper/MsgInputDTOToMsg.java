package com.sos.facemash.DTO.mapper;

import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.entity.Msg;
import com.sos.facemash.entity.User;

import java.sql.Timestamp;
import java.util.Date;

public class MsgInputDTOToMsg {
    private MsgInputDTOToMsg() {
    }

    public static Msg map(MsgInputDTO msgDTO, User destination) {
        return new Msg.Builder()
                .setTitle(msgDTO.getTitle())
                .setBody(msgDTO.getBody())
                .setDate(new Timestamp(new Date().getTime()))
                .setDestination(destination)
                .build();
    }
}
