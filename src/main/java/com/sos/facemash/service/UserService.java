package com.sos.facemash.service;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;

public interface UserService {
    UsersDTO getAllUsers(String filter);

    UserDetailDTO getUser(String userName);

    UserDetailDTO createUser(UserInputDTO user);

    UserDetailDTO modifyUser(String userName, UserInputDTO user);

    void deleteUser(String userName);

}
