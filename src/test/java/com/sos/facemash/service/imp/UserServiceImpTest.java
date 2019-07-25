package com.sos.facemash.service.imp;


import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UsersDTO;
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
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
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
    }

    @Test
    public void getAllUsersFulfilledListWithFilterTest() {
        String filter = UserUtils.random.toString();
        List<User> userTaggedList = UserUtils.userRandomListGenerator(filter);
        List<User> userList = UserUtils.userRandomListGenerator();
        userList.addAll(userTaggedList);
        when(userDAOMock.findAll()).thenReturn(userList);
        UsersDTO resultDTO = userServiceTest.getAllUsers(filter);
        assertThat(resultDTO.getUsers().size(), is(userTaggedList.size()));
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

    @Test
    public void createUser() {
    }

    @Test
    public void modifyUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void addFriend() {
    }

    @Test
    public void addFriends() {
    }

    @Test
    public void deleteFriend() {
    }
}