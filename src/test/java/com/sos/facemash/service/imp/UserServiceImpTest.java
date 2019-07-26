package com.sos.facemash.service.imp;


import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.DTO.mapper.UserInputDTOToUser;
import com.sos.facemash.core.Exceptions.DuplicatedException;
import com.sos.facemash.core.Exceptions.UserNotFoundException;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.UserDAO;
import com.sos.facemash.service.UserService;
import com.sos.facemash.service.imp.utils.UserUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImpTest {
    @Mock
    private UserDAO userDAOMock;
    private UserService userServiceTest;

    @Before
    public void setUp() {
        userServiceTest = new UserServiceImp(userDAOMock);
    }

    @Test
    public void getAllUsersEmptyListTest() {
        when(userDAOMock.findAll()).thenReturn(new ArrayList<>());
        UsersDTO resultDTO = userServiceTest.getAllUsers("");
        assertThat(resultDTO.getUsers(), empty());
    }

    @Test
    public void getAllUsersFulfilledListWithoutFilterTest() {
        List<User> userList = UserUtils.userRandomListGenerator();
        when(userDAOMock.findAll()).thenReturn(userList);
        UsersDTO resultDTO = userServiceTest.getAllUsers("");
        assertThat(resultDTO.getUsers().size(), is(userList.size()));
        resultDTO.getUsers().stream()
                .flatMap(userList.stream()


                )
                .forEach(userSummaryDTO -> {
                    assertThat(userSummaryDTO.getUserName().contains(commonTag), is(true));
                    assertThat(userSummaryDTO.getMail().contains(commonTag), is(true));
                });
    }



    @Test
    public void getAllUsersFulfilledListWithFilterTest() {
        String commonTag = UserUtils.random.toString();
        List<User> userTaggedList = UserUtils.userRandomListGenerator(commonTag);
        List<User> userList = UserUtils.userRandomListGenerator();
        userList.addAll(userTaggedList);
        when(userDAOMock.findAll()).thenReturn(userList);
        UsersDTO resultDTO = userServiceTest.getAllUsers(commonTag);
        assertThat(resultDTO.getUsers().size(), is(userTaggedList.size()));
        resultDTO.getUsers()
                .forEach(userSummaryDTO -> {
                    assertThat(userSummaryDTO.getUserName().contains(commonTag), is(true));
                    assertThat(userSummaryDTO.getMail().contains(commonTag), is(true));
                });
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserDetailNotFoundTest() {
        when(userDAOMock.findByUserName(any())).thenReturn(Optional.empty());
        userServiceTest.getUserDetail(UserUtils.random.toString());
    }

    @Test
    public void getUserDetailFoundTest() {
        Optional<User> optionalUser = Optional.of(UserUtils.UserRandomGenerator());
        when(userDAOMock.findByUserName(any())).thenReturn(optionalUser);
        UserDetailDTO resultDTO = userServiceTest.getUserDetail(any());

        assertThat(resultDTO.getUserName(), is(optionalUser.get().getUserName()));
        assertThat(resultDTO.getName(), is(optionalUser.get().getName()));
        assertThat(resultDTO.getLastName(), is(optionalUser.get().getLastName()));
        assertThat(resultDTO.getMail(), is(optionalUser.get().getMail()));
        assertThat(resultDTO.getPhone(), is(optionalUser.get().getPhone()));
    }

    @Test(expected = DuplicatedException.class)
    public void createUserThrowsDuplicatedExceptionTest() {
        when(userDAOMock.findByUserName(any())).thenReturn(Optional.of(UserUtils.UserRandomGenerator()));
        userServiceTest.createUser(UserUtils.UserInputDTORandomGenerator());
    }

    @Test
    public void createUserWorksTest() {
        UserInputDTO userInputDTO = UserUtils.UserInputDTORandomGenerator();
        User user = UserInputDTOToUser.map(userInputDTO);

        when(userDAOMock.findByUserName(any())).thenReturn(Optional.empty());
        when(userDAOMock.save(any())).thenReturn(user);

        UserDetailDTO resultDTO = userServiceTest.createUser(userInputDTO);

        assertThat(resultDTO.getUserName(), is(user.getUserName()));
        assertThat(resultDTO.getName(), is(user.getName()));
        assertThat(resultDTO.getLastName(), is(user.getLastName()));
        assertThat(resultDTO.getPhone(), is(user.getPhone()));
    }

    @Test(expected = UserNotFoundException.class)
    public void modifyUserThrowsNotFoundTest() {
        when(userDAOMock.findByUserName(any())).thenReturn(Optional.empty());
        userServiceTest.modifyUser(UserUtils.randomStringGenerator(), UserUtils.UserInputDTORandomGenerator());
    }
    @Test
    public void modifyUserWorksTest() {
        User user = UserUtils.UserRandomGenerator();
        Optional<User> optionalUser = Optional.of(user);
        UserInputDTO userInputDTO = UserUtils.UserInputDTORandomGenerator();
        User userModified = user.updateUser(UserInputDTOToUser.map(userInputDTO));

        when(userDAOMock.findByUserName(any())).thenReturn(optionalUser);
        when(userDAOMock.save(any())).thenReturn(userModified);

        UserDetailDTO resultDTO = userServiceTest.modifyUser(UserUtils.randomStringGenerator(), UserUtils.UserInputDTORandomGenerator());

        assertThat(resultDTO.getUserName(), is(userModified.getUserName()));
        assertThat(resultDTO.getName(), is(userModified.getName()));
        assertThat(resultDTO.getLastName(), is(userModified.getLastName()));
        assertThat(resultDTO.getPhone(), is(userModified.getPhone()));
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteUserThrowsUserNotFoundTest() {
        when(userDAOMock.findByUserName(any())).thenReturn(Optional.empty());
        userServiceTest.deleteUser(UserUtils.randomStringGenerator());
    }

    @Test(expected = UserNotFoundException.class)
    public void addFriendButUserNotFoundTest() {
        String userName = UserUtils.randomStringGenerator();
        when(userDAOMock.findByUserName(userName)).thenReturn(Optional.empty());
        userServiceTest.addFriend(userName,UserUtils.randomStringGenerator());
    }
    @Test(expected = UserNotFoundException.class)
    public void addFriendButFriendNotFoundTest() {
        String friendUserName = UserUtils.randomStringGenerator();
        String userName = UserUtils.randomStringGenerator();
        when(userDAOMock.findByUserName(userName)).thenReturn(Optional.of(UserUtils.UserRandomGenerator()));
        when(userDAOMock.findByUserName(friendUserName)).thenReturn(Optional.empty());
        userServiceTest.addFriend(userName,UserUtils.randomStringGenerator());
    }

    @Test
    public void addFriends() {

    }

    @Test
    public void deleteFriend() {
    }
}