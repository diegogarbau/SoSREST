package com.sos.facemash.service;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.entity.User;

public interface UserService {

    UsersDTO getAllUsers(String filter);

    UsersDTO getAllUsers();

    UserDetailDTO getUserDetail(String userName);

    User getUser(String userName);
    User getUserOptional(String userName);

    UserDetailDTO createUser(UserInputDTO user);

    UserDetailDTO modifyUser(String userName, UserInputDTO user);

    UserDetailDTO saveUser(User user);

    void deleteUser(String userName);

    UsersDTO addFriend(String userName, String friendUserName);

    UsersDTO deleteFriend(String userName, String friendUserName);

    UsersDTO getFriends(String userName);
}
