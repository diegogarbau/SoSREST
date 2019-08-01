package com.sos.facemash.controller;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.DTO.MsgssDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Msg")
public interface MsgController {

    @ApiOperation(value = "Msg", notes = "Endpoint to get all the messages from a user with filter and selected number of elements to show ", response = MsgssDTO.class)
    MsgssDTO getAllMessages(String userName, String filter, int nElements);

    @ApiOperation(value = "Msg", notes = "Endpoint to get a message from a user", response = MsgDetailDTO.class)
    MsgDetailDTO getMessage(String userName, Long msgId);

    @ApiOperation(value = "Msg", notes = "Endpoint to create a user", response = MsgDetailDTO.class)
    MsgDetailDTO createMsg(String userName, MsgInputDTO msgInputDTO);

    @ApiOperation(value = "Msg", notes = "Endpoint to modify a previous message of a user", response = MsgDetailDTO.class)
    MsgDetailDTO modifyMsg(String userName, Long msgId, MsgInputDTO MsgInputDTO);

    @ApiOperation(value = "Msg", notes = "Endpoint to delete a previous message from a user")
    void deleteMsg(String userName, Long msgId);
}
