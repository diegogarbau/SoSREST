package com.sos.facemash.service.impl;

import com.sos.facemash.DTO.MsgSummaryDTO;
import com.sos.facemash.DTO.MsgssDTO;
import com.sos.facemash.core.Exceptions.MsgNotFoundException;
import com.sos.facemash.core.Exceptions.UserNotFoundException;
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
        msgServiceTest.getAllMsg(CoreUtils.randomStringGenerator(), CoreUtils.randomStringGenerator());
    }

    @Test
    public void getAllMsgFulFilledWithOutFilterTest() {
        User user = UserUtils.UserRandomGenerator();
        List<Msg> msgList = MsgUtils.msgOfUserRandomListGenerator(user);
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.findAllByOwner(any())).thenReturn(msgList);
        MsgssDTO resultDTO = msgServiceTest.getAllMsg(CoreUtils.randomStringGenerator(), "");
        assertThat(resultDTO.getMsgss().size(), is(msgList.size()));
        resultDTO.getMsgss()
                .forEach(msgSummaryDTO -> {
                    assertThat(msgSummaryDTO.getOwner(), is(user));
                    assertThat(messageIsContained(msgList, msgSummaryDTO.getTitle()), is(true));
                });
    }

    @Test
    public void getAllMsgFulFilledWithFilterTest() {
        User user = UserUtils.UserRandomGenerator();
        String common = CoreUtils.randomStringGenerator();
        List<Msg> msgFilteredList = MsgUtils.msgOfUserAndFilterRandomListGenerator(user, common);
        List<Msg> msgList = MsgUtils.msgListOfSeveralUsersGenerator();
        when(userServiceMock.getUser(any())).thenReturn(user);
        when(msgDAOMock.findAllByOwner(any())).thenReturn(msgList);
        msgList.addAll(msgFilteredList);
        MsgssDTO resultDTO = msgServiceTest.getAllMsg(CoreUtils.randomStringGenerator(), common);
        assertThat(resultDTO.getMsgss().size(), is(msgFilteredList.size()));
        resultDTO.getMsgss()
                .forEach(msgSummaryDTO -> {
                    assertThat(msgSummaryDTO.getOwner(), is(user));
                    assertThat(msgSummaryDTO.getTitle(), containsString(common));
                    assertThat(messageIsContained(msgList, msgSummaryDTO.getTitle()), is(true));
                });
    }

    @Test(expected = MsgNotFoundException.class)
    public void getMsgNotFoundTest() {
        when(msgDAOMock.findByIdAndOwner(any(), any())).thenReturn(Optional.empty());
        msgServiceTest.getMsg(CoreUtils.randomStringGenerator(), CoreUtils.random.nextLong());
    }

    @Test
    public void getMsgWorksTest() {

    }

    @Test
    public void createMsg() {
    }

    @Test
    public void modifyMsg() {
    }

    @Test
    public void deleteMsg() {
    }

    private boolean messageIsContained(List<Msg> msgList, String title) {
        return msgList.stream().anyMatch(msg -> msg.getTitle().contains(title));
    }

    private boolean msgSummaryDTOisContained(List<MsgSummaryDTO> msgList, String filter) {
        return msgList.stream().anyMatch(msgSummaryDTO -> msgSummaryDTO.getTitle().contains(filter));
    }
}