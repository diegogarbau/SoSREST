package com.sos.facemash.DTO.mapper;

import com.sos.facemash.DTO.MsgSummaryDTO;
import com.sos.facemash.entity.Msg;

public class MsgToMsgSummaryDTO {
    private MsgToMsgSummaryDTO() {
    }

    public static MsgSummaryDTO map(Msg msg) {
        return new MsgSummaryDTO.Builder()
                .setTitle(msg.getTitle())
                .setDate(msg.getDate())
                .build();
    }
}
