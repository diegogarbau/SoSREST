package com.sos.facemash.service.impl;


import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UserSummaryDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.DTO.mapper.UserInputDTOToUser;
import com.sos.facemash.core.exceptions.usersExceptions.AlreadyFriendsException;
import com.sos.facemash.core.exceptions.usersExceptions.DuplicatedUserException;
import com.sos.facemash.core.exceptions.usersExceptions.NotFriendsException;
import com.sos.facemash.core.exceptions.usersExceptions.UserNotFoundException;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.UserDAO;
import com.sos.facemash.service.UserService;
import com.sos.facemash.utils.UserUtils;
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
public class UserServiceTest {
    @Mock
    private UserDAO userDAOMock;
    private UserService userServiceTest;

    @Before
    public void setUp() {
        userServiceTest = new UserServiceImpl(userDAOMock);
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
        resultDTO.getUsers()
                .forEach(userSummaryDTO ->
                        assertThat(userIsContained(userList, userSummaryDTO.getUserName()), is(true)));
    }


    @Test
    public void getAllUsersFulfilledListWithFilterTest() {
        String commonTag = UserUtils.randomStringGenerator();
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
                    assertThat(userIsContained(userTaggedList, userSummaryDTO.getUserName()), is(true));
                });
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserDetailNotFoundTest() {
        when(userDAOMock.findByUserName(any())).thenReturn(Optional.empty());
        userServiceTest.getUserDetail(UserUtils.randomStringGenerator());
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

    @Test(expected = DuplicatedUserException.class)
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
    public void deleteUserWorksTest() {
        when(userDAOMock.findByUserName(any())).thenReturn(Optional.empty());
        userServiceTest.deleteUser(UserUtils.randomStringGenerator());
    }

    @Test(expected = UserNotFoundException.class)
    public void addFriendButUserNotFoundTest() {
        String userName = UserUtils.randomStringGenerator();
        when(userDAOMock.findByUserName(userName)).thenReturn(Optional.empty());
        userServiceTest.addFriend(userName, UserUtils.randomStringGenerator());
    }

    @Test(expected = UserNotFoundException.class)
    public void addFriendButFriendNotFoundTest() {
        String friendUserName = UserUtils.randomStringGenerator();
        String userName = UserUtils.randomStringGenerator();

        when(userDAOMock.findByUserName(userName)).thenReturn(Optional.of(UserUtils.UserRandomGenerator()));
        when(userDAOMock.findByUserName(friendUserName)).thenReturn(Optional.empty());

        userServiceTest.addFriend(userName, friendUserName);
    }

    @Test
    public void addFriendWorksTest() {
        User user = UserUtils.UserRandomGenerator();
        User friend = UserUtils.UserRandomGenerator();
        User userModified = new User.Builder().build().cloneUser(user);
        userModified.getFriends().add(friend);

        when(userDAOMock.findByUserName(user.getUserName())).thenReturn(Optional.of(user));
        when(userDAOMock.findByUserName(friend.getUserName())).thenReturn(Optional.of(friend));
        when(userDAOMock.save(any())).thenReturn(userModified);

        UsersDTO resultDTO = userServiceTest.addFriend(user.getUserName(), friend.getUserName());

        assertThat(userSummaryDTOisContained(resultDTO.getUsers(), friend.getUserName()), is(true));
    }

    @Test(expected = AlreadyFriendsException.class)
    public void addFriendButAlreadyFriendsTest() {
        User user = UserUtils.UserRandomGenerator();
        User friend = UserUtils.UserRandomGenerator();
        user.getFriends().add(friend);

        when(userDAOMock.findByUserName(user.getUserName())).thenReturn(Optional.of(user));
        when(userDAOMock.findByUserName(friend.getUserName())).thenReturn(Optional.of(friend));

        userServiceTest.addFriend(user.getUserName(), friend.getUserName());
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteFriendButUserNotFoundTest() {
        String userName = UserUtils.randomStringGenerator();
        when(userDAOMock.findByUserName(userName)).thenReturn(Optional.empty());
        userServiceTest.deleteFriend(userName, UserUtils.randomStringGenerator());
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteFriendButFriendNotFoundTest() {
        String userName = UserUtils.randomStringGenerator();
        String friendUserName = UserUtils.randomStringGenerator();

        when(userDAOMock.findByUserName(userName)).thenReturn(Optional.of(UserUtils.UserRandomGenerator()));
        when(userDAOMock.findByUserName(friendUserName)).thenReturn(Optional.empty());

        userServiceTest.deleteFriend(userName, friendUserName);
    }

    @Test(expected = NotFriendsException.class)
    public void deleteFriendButNotFriendsTest() {
        String userName = UserUtils.randomStringGenerator();
        String friendUserName = UserUtils.randomStringGenerator();

        when(userDAOMock.findByUserName(userName)).thenReturn(Optional.of(UserUtils.UserRandomGenerator()));
        when(userDAOMock.findByUserName(friendUserName)).thenReturn(Optional.of(UserUtils.UserRandomGenerator()));

        userServiceTest.deleteFriend(userName, friendUserName);
    }

    @Test
    public void deleteFriendWorksTest() {
        User user = UserUtils.UserRandomGenerator();
        User friend = UserUtils.UserRandomGenerator();
        User userModified = new User.Builder().build().cloneUser(user);
        user.getFriends().add(friend);
        userModified.getFriends().remove(friend);

        when(userDAOMock.findByUserName(user.getUserName())).thenReturn(Optional.of(user));
        when(userDAOMock.findByUserName(friend.getUserName())).thenReturn(Optional.of(friend));
        when(userDAOMock.save(any())).thenReturn(userModified);

        UsersDTO resultDTO = userServiceTest.deleteFriend(user.getUserName(), friend.getUserName());
        assertThat(userSummaryDTOisContained(resultDTO.getUsers(), friend.getUserName()), is(false));
    }

    @Test(expected = UserNotFoundException.class)
    public void getFriendsUserNotFoundTest() {
        when(userDAOMock.findByUserName(any())).thenReturn(Optional.empty());
        userServiceTest.getFriends(UserUtils.randomStringGenerator());
    }

    @Test(expected = UserNotFoundException.class)
    public void getFriendsWorksTest() {
        User user = UserUtils.UserRandomGenerator();
        List<User> userList = UserUtils.userRandomListGenerator();
        user.setFriends(userList);

        UsersDTO resultDTO = userServiceTest.getFriends(UserUtils.randomStringGenerator());
        resultDTO.getUsers()
                .forEach(userSummaryDTO ->
                        assertThat(userIsContained(userList, userSummaryDTO.getUserName()), is(true)));
    }

    private boolean userIsContained(List<User> userList, String userName) {
        return userList.stream().anyMatch(user -> user.getUserName().equals(userName));
    }

    private boolean userSummaryDTOisContained(List<UserSummaryDTO> userList, String userName) {
        return userList.stream().anyMatch(userSummaryDTO -> userSummaryDTO.getUserName().equals(userName));
    }
}
