package com.sos.facemash.utils;

import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserUtils extends CoreUtils {

    private static User UserRandomGenerator(String common) {
        String userTag = common + randomStringGenerator();
        return new User.Builder()
                .setUserName(userTag)
                .setName(userTag + "-name")
                .setLastName(userTag + "-LastName")
                .setMail(userTag + "@mail.com")
                .setPhone(random.nextInt(999999999))
                .setFriends(new ArrayList<>())
                .setFriendsOf(new ArrayList<>())
                .setMessagesSent(new ArrayList<>())
                .setMessagesReceived(new ArrayList<>())
                .build();
    }

    public static User UserRandomGenerator() {
        String userTag = randomStringGenerator();
        return new User.Builder()
                .setUserName(userTag)
                .setName(userTag + "-name")
                .setLastName(userTag + "-LastName")
                .setMail(userTag + "@mail.com")
                .setPhone(random.nextInt(999999999))
                .setFriends(new ArrayList<>())
                .setFriendsOf(new ArrayList<>())
                .setMessagesSent(new ArrayList<>())
                .setMessagesReceived(new ArrayList<>())
                .build();
    }

    public static UserInputDTO UserInputDTORandomGenerator() {
        String userTag = randomStringGenerator();
        return new UserInputDTO.Builder()
                .setUserName(userTag)
                .setName(userTag + "-name")
                .setLastName(userTag + "-LastName")
                .setMail(userTag + "@mail.com")
                .setPhone(random.nextInt(999999999))
                .build();
    }

    public static List<User> userRandomListGenerator() {
        return IntStream.rangeClosed(0, random.nextInt(8) + 1)
                .mapToObj(i -> UserRandomGenerator())
                .collect(Collectors.toList());
    }

    public static List<User> userRandomListGenerator(String common) {
        return IntStream.rangeClosed(0, random.nextInt(8) + 1)
                .mapToObj(i -> UserRandomGenerator(common))
                .collect(Collectors.toList());
    }

}
