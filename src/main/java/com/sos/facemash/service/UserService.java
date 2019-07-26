package com.sos.facemash.service;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.entity.User;

import java.util.List;

public interface UserService {

    UsersDTO getAllUsers(String filter);

    UserDetailDTO getUserDetail(String userName);

    User getUser(String userName);

    UserDetailDTO createUser(UserInputDTO user);

    UserDetailDTO modifyUser(String userName, UserInputDTO user);

    void deleteUser(String userName);

    UsersDTO addFriend(String userName, String friendUserName);

    UsersDTO deleteFriend(String userName, String friendUserName);

    UsersDTO getFriends(String userName);
}
