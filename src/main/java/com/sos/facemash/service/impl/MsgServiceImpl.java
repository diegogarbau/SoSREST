package com.sos.facemash.service.impl;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.DTO.MsgssDTO;
import com.sos.facemash.DTO.mapper.MsgInputDTOToMsg;
import com.sos.facemash.DTO.mapper.MsgToMsgDetailDTO;
import com.sos.facemash.DTO.mapper.MsgToMsgSummaryDTO;
import com.sos.facemash.core.Exceptions.MsgNotFoundException;
import com.sos.facemash.entity.Msg;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.MsgDAO;
import com.sos.facemash.service.MsgService;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
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
        Predicate<Msg> filterBycontent = msg -> msg.getTitle().contains(filter);
        User user = userService.getUser(userName);
        List<Msg> msgList = msgDAO.findAllByOwner(user);
        return new MsgssDTO(msgList
                .stream()
                .filter(filterBycontent)
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
        msg.setOwner(userService.getUser(userName));
        return MsgToMsgDetailDTO.map(msgDAO.save(msg));
    }

    @Override
    public MsgDetailDTO modifyMsg(String userName, Long msgId, MsgInputDTO msgInputDTO) {
        Msg msg = msgDAO.findByIdAndOwner(msgId, userService.getUser(userName)).orElseThrow(()
                -> new MsgNotFoundException("El mensaje no figura en la base de datos"));
        msg.updateMsg(MsgInputDTOToMsg.map(msgInputDTO));
        return MsgToMsgDetailDTO.map(msgDAO.save(msg));

    }

    @Override
    public void deleteMsg(String userName, Long msgId) {
        msgDAO.delete(msgDAO.findByIdAndOwner(msgId, userService.getUser(userName)).orElseThrow(()
                -> new MsgNotFoundException("El mensaje no figura en la base de datos")));
    }

}
