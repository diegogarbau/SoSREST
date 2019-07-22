package com.sos.facemash.DTO.mapper;

import com.sos.facemash.DTO.UserSummaryDTO;
import com.sos.facemash.entity.User;

public class UserToUserSummaryDTO {
    private UserToUserSummaryDTO() {
    }

    public static UserSummaryDTO map(User user) {
        return new UserSummaryDTO.Builder()
                .setUserName(user.getUserName())
                .setMail(user.getMail())
                .build();
    }
}
