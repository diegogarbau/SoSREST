package com.sos.facemash.service.imp;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.DTO.MsgssDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.persistance.MsgDAO;
import com.sos.facemash.service.MsgService;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class MsgServiceImpl implements MsgService {
    private MsgDAO msgDAO;
    private UserService userService;
    @Autowired
    public MsgServiceImpl(MsgDAO msgDAO, UserService userService) {
        this.msgDAO = msgDAO;
        this.userService = userService;
    }

    @Override
    public MsgssDTO getAllMsg(String userName, String filter) {
        return null;
    }

    @Override
    public MsgDetailDTO getMsg(String userName, String msgId) {
        return null;
    }

    @Override
    public MsgDetailDTO createMsg(String userName, MsgInputDTO msgInputDTO) {
        return null;
    }

    @Override
    public MsgDetailDTO modifyMsg(String userName, String msgId, UserInputDTO msgInputDTO) {
        return null;
    }

    @Override
    public void deleteMsg(String userName, String msgId) {

    }
}
