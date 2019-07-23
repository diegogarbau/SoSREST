package com.sos.facemash.service.imp;

import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.persistance.UserDAO;
import com.sos.facemash.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImpTest {
    @Mock
    private UserDAO userDAOMock;
    @InjectMocks
    private UserService userServiceTest;


    @Test
    void getAllUsersEmptyListTest() {
//        when(userDAOMock.findAll()).thenReturn(new ArrayList<>());
//        UsersDTO resultDTO = userServiceTest.getAllUsers("");
//        assertTrue(resultDTO.getUsers().isEmpty());

    }

    @Test
    void getUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void modifyUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void addFriend() {
    }

    @Test
    void addFriends() {
    }

    @Test
    void deleteFriend() {
    }
}