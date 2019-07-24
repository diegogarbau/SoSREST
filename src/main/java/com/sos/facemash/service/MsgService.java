package com.sos.facemash.service;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.DTO.MsgssDTO;
import com.sos.facemash.DTO.UserInputDTO;

public interface MsgService {
    MsgssDTO getAllMsg(String userName, String filter);

    MsgDetailDTO getMsg(String userName, Long msgId);

    MsgDetailDTO createMsg(String userName, MsgInputDTO msgInputDTO);

    MsgDetailDTO modifyMsg(String userName, Long msgId, MsgInputDTO msgInputDTO);

    void deleteMsg(String userName, Long msgId);
}
