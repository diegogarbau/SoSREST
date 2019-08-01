package com.sos.facemash.controller;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Users")
public interface UserController {
    @ApiOperation(value = "Users", notes = "Endpoint to get all the users", response = UsersDTO.class)
    UsersDTO getAllUsers();

    @ApiOperation(value = "Users", notes = "Endpoint to get all the users by filter ", response = UsersDTO.class)
    UsersDTO getAllUsers(String filter);

    @ApiOperation(value = "Users", notes = "Endpoint to get an user by userName", response = UserDetailDTO.class)
    UserDetailDTO getUser(String userName);

    @ApiOperation(value = "Users", notes = "Endpoint to create a user", response = UserDetailDTO.class)
    UserDetailDTO createUser(UserInputDTO userInputDTO);

    @ApiOperation(value = "Users", notes = "Endpoint to modify a previous user", response = UserDetailDTO.class)
    UserDetailDTO modifyUser(String userName, UserInputDTO userInputDTO);

    @ApiOperation(value = "Users", notes = "Endpoint to delete a previous user by userName")
    void deleteUser(String userName);

    @ApiOperation(value = "Users", notes = "Endpoint to add a friend to User, return user's friends")
    UsersDTO addFriend(String userName, String friendUserName);

    @ApiOperation(value = "Users", notes = "Endpoint to delete a friend of User, return user's friends")
    UsersDTO deleteFriend(String userName, String friendUserName);


    @ApiOperation(value = "Users", notes = "Endpoint to get friend list of User")
    UsersDTO getAllFriends(String userName);


}
