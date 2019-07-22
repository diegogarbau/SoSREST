package com.sos.facemash.controller.impl;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.controller.UserController;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl implements UserController {

    private UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users{filter}/")
    @Override
    public UsersDTO getAllUsers(@PathVariable("filter") String filter) {
        return userService.getAllUsers(filter);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{userName}/")
    @Override
    public UserDetailDTO getUser(@PathVariable("userName") String userName) {
        return userService.getUser(userName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    @Validated
    @Override
    public UserDetailDTO createUser(@RequestBody UserInputDTO userInputDTO) {
        return userService.createUser(userInputDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/{userName}")
    @Validated
    @Override
    public UserDetailDTO modifyUser(@PathVariable("userName") String userName, @RequestBody UserInputDTO userInputDTO) {
        return userService.modifyUser(userName, userInputDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{userName}")
    @Override
    public void deleteUser(@PathVariable("userName") String userName) {
        userService.deleteUser(userName);
    }
}
