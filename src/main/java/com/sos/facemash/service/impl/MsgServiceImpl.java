package com.sos.facemash.service.impl;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.DTO.MsgssDTO;
import com.sos.facemash.DTO.mapper.MsgInputDTOToMsg;
import com.sos.facemash.DTO.mapper.MsgToMsgDetailDTO;
import com.sos.facemash.DTO.mapper.MsgToMsgSummaryDTO;
import com.sos.facemash.core.exceptions.messagesExceptions.MsgNotFoundException;
import com.sos.facemash.entity.Msg;
import com.sos.facemash.persistance.MsgDAO;
import com.sos.facemash.service.MsgService;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MsgServiceImpl implements MsgService {
    private MsgDAO msgDAO;
    private UserService userService;

    @Autowired
    MsgServiceImpl(MsgDAO msgDAO, UserService userService) {
        this.msgDAO = msgDAO;
        this.userService = userService;
    }

    @Override
    public MsgssDTO getAllMsg(String userName, String filter, int nElements) {
        return new MsgssDTO(msgDAO.findAllByOwner(userService.getUser(userName))
                .stream()
                .filter(msg -> msg.getTitle().contains(filter))
                .limit((nElements == 0) ? 10 : nElements)
                .map(MsgToMsgSummaryDTO::map)
                .collect(Collectors.toList()));
    }

    @Override
    public MsgDetailDTO getMsg(String userName, Long msgId) {
        return MsgToMsgDetailDTO.map(msgDAO.findByIdAndOwner(msgId, userService.getUser(userName)).orElseThrow(()
                -> new MsgNotFoundException("El mensaje no figura en la base de datos")));
    }

    @Override
    public MsgDetailDTO createMsg(String userName, MsgInputDTO msgInputDTO) {
        Msg msg = MsgInputDTOToMsg.map(msgInputDTO);
        return saveMsg(msg.setOwner(userService.getUser(userName)));
    }

    @Override
    public MsgDetailDTO modifyMsg(String userName, Long msgId, MsgInputDTO msgInputDTO) {
        Msg msg = msgDAO.findByIdAndOwner(msgId, userService.getUser(userName)).orElseThrow(()
                -> new MsgNotFoundException("El mensaje no figura en la base de datos"));
        msg.updateMsg(MsgInputDTOToMsg.map(msgInputDTO));
        if (msgInputDTO.getDestinationId() != null) {
            msg.setDestination(userService.getUser(msgInputDTO.getDestinationId()));
        }
        return saveMsg(msg);
    }

    private MsgDetailDTO saveMsg(Msg msg) {
        if (msg.getDestination() != null) {
            msg.getDestination().getMessagesReceived().add(msg);
            userService.saveUser(msg.getDestination());
        }
        msg.getOwner().getMessagesSent().add(msg);
        userService.saveUser(msg.getOwner());
        return MsgToMsgDetailDTO.map(msgDAO.save(msg));
    }

    @Override
    public void deleteMsg(String userName, Long msgId) {
        msgDAO.delete(msgDAO.findByIdAndOwner(msgId, userService.getUser(userName)).orElseThrow(()
                -> new MsgNotFoundException("El mensaje no figura en la base de datos")));
    }

}
