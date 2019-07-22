package com.sos.facemash.service;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;

import java.util.List;

public interface UserService {
    UsersDTO getAllUsers(String filter);

    UserDetailDTO getUser(String userName);

    UserDetailDTO createUser(UserInputDTO user);

    UserDetailDTO modifyUser(String userName, UserInputDTO user);

    void deleteUser(String userName);

    UsersDTO addFriend(String userName, String friendUserName);

    UsersDTO addFriends(String userName, List<String> friendsList);

    UsersDTO deleteFriend(String userName, String friendUserName);
}
