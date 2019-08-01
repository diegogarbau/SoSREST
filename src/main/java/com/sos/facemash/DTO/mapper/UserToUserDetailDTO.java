package com.sos.facemash.DTO.mapper;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.entity.User;

import java.util.stream.Collectors;

public class UserToUserDetailDTO {
    private UserToUserDetailDTO() {
    }

    public static UserDetailDTO map(User user) {
        return new UserDetailDTO.Builder()
                .setUserName(user.getUserName())
                .setMail(user.getMail())
                .setPhone(user.getPhone())
                .setName(user.getName())
                .setLastName(user.getLastName())
                .setFriends(user.getFriends().stream().map(UserToUserSummaryDTO::map).collect(Collectors.toList()))
                .setMessagesSent(user.getMessagesSent().stream().map(MsgToMsgSummaryDTO::map).collect(Collectors.toList()))
                .setMessagesReceived(user.getMessagesReceived().stream().map(MsgToMsgSummaryDTO::map).collect(Collectors.toList()))
                .build();
    }
}
