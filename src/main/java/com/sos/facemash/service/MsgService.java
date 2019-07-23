package com.sos.facemash.service;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.DTO.MsgssDTO;
import com.sos.facemash.DTO.UserInputDTO;

public interface MsgService {
    MsgssDTO getAllMsg(String userName, String filter);

    MsgDetailDTO getMsg(String userName, String msgId);

    MsgDetailDTO createMsg(String userName, MsgInputDTO msgInputDTO);

    MsgDetailDTO modifyMsg(String userName, String msgId, UserInputDTO msgInputDTO);

    void deleteMsg(String userName, String msgId);
}
