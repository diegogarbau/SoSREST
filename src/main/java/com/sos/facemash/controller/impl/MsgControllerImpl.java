package com.sos.facemash.controller.impl;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.DTO.MsgssDTO;
import com.sos.facemash.controller.MsgController;
import com.sos.facemash.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class MsgControllerImpl implements MsgController {
    private MsgService msgService;

    @Autowired
    public MsgControllerImpl(MsgService msgService) {
        this.msgService = msgService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userName}/messages/")
    @Override
    public MsgssDTO getAllMessages(@PathVariable("userName") String userName,
                                   @RequestParam(value = "filter",required = false) String filter,
                                   @RequestParam(value = "nElements",required = false,defaultValue = "10") int nElements) {
        return msgService.getAllMsg(userName, filter,nElements);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userName}/message/{msgId}/")
    @Override
    public MsgDetailDTO getMessage(@PathVariable("userName") String userName,
                                   @PathVariable("msgId") Long msgId) {
        return msgService.getMsg(userName, msgId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userName}/message/")
    @Override
    public MsgDetailDTO createMsg(@PathVariable("userName") String userName,
                                  @RequestBody @Valid MsgInputDTO msgInputDTO) {
        return msgService.createMsg(userName, msgInputDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userName}/message/{msgId}/")
    @Override
    public MsgDetailDTO modifyMsg(@PathVariable("userName") String userName,
                                  @PathVariable("msgId") Long msgId,
                                  @RequestBody @Valid MsgInputDTO msgInputDTO) {
        return msgService.modifyMsg(userName, msgId, msgInputDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userName}/message/{msgId}/")
    @Override
    public void deleteMsg(@PathVariable("userName") String userName,
                          @PathVariable("msgId") Long msgId) {
        msgService.deleteMsg(userName, msgId);
    }

}
