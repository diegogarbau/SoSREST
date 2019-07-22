package com.sos.facemash.DTO.mapper;

import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.entity.User;

public class UserInputDTOToUser {
    private UserInputDTOToUser() {
    }

    public static User map(UserInputDTO user) {
        return new User.Builder()
                .setUserName(user.getUserName())
                .setMail(user.getMail())
                .setPhone(user.getPhone())
                .setName(user.getName())
                .setLastName(user.getLastname())
                .build();
    }
}
