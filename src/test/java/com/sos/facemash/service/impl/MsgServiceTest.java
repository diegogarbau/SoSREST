package com.sos.facemash.service.impl;

import com.sos.facemash.DTO.MsgDetailDTO;
import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.DTO.MsgssDTO;
import com.sos.facemash.DTO.mapper.MsgInputDTOToMsg;
import com.sos.facemash.core.exceptions.messagesExceptions.MsgNotFoundException;
import com.sos.facemash.core.exceptions.usersExceptions.UserNotFoundException;
import com.sos.facemash.entity.Msg;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.MsgDAO;
import com.sos.facemash.service.MsgService;
import com.sos.facemash.service.UserService;
import com.sos.facemash.utils.CoreUtils;
import com.sos.facemash.utils.MsgUtils;
import com.sos.facemash.utils.UserUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MsgServiceTest {

    private MsgService msgServiceTest;
    @Mock
    private MsgDAO msgDAOMock;
    @Mock
    private UserService userServiceMock;


    @Before
    public void setUp() {
        msgServiceTest = new MsgServiceImpl(msgDAOMock, userServiceMock);
    }

    @Test(expected = UserNotFoundException.class)
    public void getAllMsgButUserNotFoundTest() {
        when(userServiceMock.getUser(any())).thenThrow(new UserNotFoundException(""));
        msgServiceTest.getAllMsg(CoreUtils.randomStringGenerator(), CoreUtils.randomStringGenerator(),CoreUtils.RANDOM.nextInt(10));
    }

    @Test
    public void getAllMsgFulFilledWithOutFilterTest() {
        User user = UserUtils.UserRandomGenerator();
        List<Msg> msgList = MsgUtils.msgOfUserRandomListGenerator(user);
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.findAllByOwner(any())).thenReturn(msgList);
        MsgssDTO resultDTO = msgServiceTest.getAllMsg(CoreUtils.randomStringGenerator(), "",1000);
        assertThat(resultDTO.getMsgss().size(), is(msgList.size()));
        resultDTO.getMsgss()
                .forEach(msgSummaryDTO -> assertThat(messageIsContained(msgList, msgSummaryDTO.getTitle()), is(true)));
    }

    @Test
    public void getAllMsgFulFilledWithFilterTest() {
        User user = UserUtils.UserRandomGenerator();
        String common = CoreUtils.randomStringGenerator();
        List<Msg> msgFilteredList = MsgUtils.msgOfUserAndFilterRandomListGenerator(user, common);
        List<Msg> msgList = MsgUtils.msgListOfSeveralUsersGenerator(common);
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.findAllByOwner(any())).thenReturn(msgList);
        msgList.addAll(msgFilteredList);
        MsgssDTO resultDTO = msgServiceTest.getAllMsg(CoreUtils.randomStringGenerator(), common,100);
        assertThat(resultDTO.getMsgss().size(), is(msgFilteredList.size()));
        resultDTO.getMsgss()
                .forEach(msgSummaryDTO -> {
                    assertThat(msgSummaryDTO.getTitle(), containsString(common));
                    assertThat(messageIsContained(msgList, msgSummaryDTO.getTitle()), is(true));
                });
    }

    @Test(expected = MsgNotFoundException.class)
    public void getMsgNotFoundTest() {
        when(msgDAOMock.findByIdAndOwner(any(), any())).thenReturn(Optional.empty());
        msgServiceTest.getMsg(CoreUtils.randomStringGenerator(), CoreUtils.RANDOM.nextLong());
    }

    @Test
    public void getMsgWorksTest() {
        User user = UserUtils.UserRandomGenerator();
        Msg msg = MsgUtils.ownMsgRandomGenerator(user);
        when(msgDAOMock.findByIdAndOwner(any(), any())).thenReturn(Optional.of(msg));
        MsgDetailDTO resultDTO = msgServiceTest.getMsg(user.getUserName(), CoreUtils.RANDOM.nextLong());
        assertThat(resultDTO.getOwner(), is(user.getUserName()));
        assertThat(resultDTO.getTitle(), is(msg.getTitle()));
        assertThat(resultDTO.getBody(), is(msg.getBody()));
        assertThat(resultDTO.getDate(), is(msg.getDate()));
    }

    @Test
    public void createMsgWorksTest() {
        User user = UserUtils.UserRandomGenerator();
        MsgInputDTO msgInput = MsgUtils.msgInputDTORandomGenerator();
        Msg savedMsg = MsgInputDTOToMsg.map(msgInput,null);
        savedMsg.setOwner(user);

        when(userServiceMock.getUserOptional(any())).thenReturn(null);
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.save(any())).thenReturn(savedMsg);


        MsgDetailDTO resultDTO = msgServiceTest.createMsg(user.getUserName(),msgInput );
        assertThat(resultDTO.getOwner(), is(user.getUserName()));
        assertThat(resultDTO.getTitle(), is(msgInput.getTitle()));
        assertThat(resultDTO.getBody(), is(msgInput.getBody()));
    }

    @Test(expected = UserNotFoundException.class)
    public void createMsgButUserNotFoundTest() {
        MsgInputDTO msgInput = MsgUtils.msgInputDTORandomGenerator();
        when(userServiceMock.getUser(any())).thenThrow(new UserNotFoundException(""));
        msgServiceTest.createMsg(CoreUtils.randomStringGenerator(), msgInput);
    }
    @Test(expected = UserNotFoundException.class)
    public void modifyMsgButUserNotFoundTest() {
        MsgInputDTO msgInput = MsgUtils.msgInputDTORandomGenerator();
        when(userServiceMock.getUser(any())).thenThrow(new UserNotFoundException(""));
        msgServiceTest.modifyMsg(CoreUtils.randomStringGenerator(), CoreUtils.RANDOM.nextLong(), msgInput);
    }

    @Test(expected = MsgNotFoundException.class)
    public void modifyMsgButMsgNotFoundTest() {
        User user = UserUtils.UserRandomGenerator();
        MsgInputDTO msgInput = MsgUtils.msgInputDTORandomGenerator();
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.findByIdAndOwner(any(), any())).thenReturn(Optional.empty());
        msgServiceTest.modifyMsg(CoreUtils.randomStringGenerator(), CoreUtils.RANDOM.nextLong(), msgInput);
    }

    @Test
    public void modifyMsgWorksTest() {
        User user = UserUtils.UserRandomGenerator();
        MsgInputDTO msgInput = MsgUtils.msgInputDTORandomGenerator();
        Msg msg = MsgUtils.ownMsgRandomGenerator(user);
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.findByIdAndOwner(any(), any())).thenReturn(Optional.of(msg));
        Msg updatedMsg = msg.updateMsg(MsgInputDTOToMsg.map(msgInput,null));
        when(msgDAOMock.save(any())).thenReturn(updatedMsg);
        MsgDetailDTO resultDTO = msgServiceTest.modifyMsg(CoreUtils.randomStringGenerator(), CoreUtils.RANDOM.nextLong(), msgInput);
        assertThat(resultDTO.getOwner(), is(user.getUserName()));
        assertThat(resultDTO.getTitle(), is(msgInput.getTitle()));
        assertThat(resultDTO.getBody(), is(msgInput.getBody()));
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteMsgButUserNotFoundTest() {
        when(userServiceMock.getUser(any())).thenThrow(new UserNotFoundException(""));
        msgServiceTest.deleteMsg(CoreUtils.randomStringGenerator(), CoreUtils.RANDOM.nextLong());
    }

    @Test(expected = MsgNotFoundException.class)
    public void deleteMsgButMsgNotFoundTest() {
        User user = UserUtils.UserRandomGenerator();
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.findByIdAndOwner(any(), any())).thenReturn(Optional.empty());
        msgServiceTest.deleteMsg(CoreUtils.randomStringGenerator(), CoreUtils.RANDOM.nextLong());
    }

    @Test
    public void deleteMsgWorksTest() {
        User user = UserUtils.UserRandomGenerator();
        Msg msg = MsgUtils.ownMsgRandomGenerator(user);
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.findByIdAndOwner(any(), any())).thenReturn(Optional.of(msg));
        msgServiceTest.deleteMsg(CoreUtils.randomStringGenerator(), CoreUtils.RANDOM.nextLong());
        assertThat(true, is(true));
    }

    private boolean messageIsContained(List<Msg> msgList, String title) {
        return msgList.stream().anyMatch(msg -> msg.getTitle().contains(title));
    }
}