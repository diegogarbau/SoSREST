package com.sos.facemash.service.imp.utils;

import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.entity.User;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserUtils {
    public static Random random = new Random();

    private static String randomStringGenerator() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 7);
    }

    public static User UserRandomGenerator(String common) {
        String userTag = common+randomStringGenerator();
        return new User.Builder()
                .setUserName(userTag)
                .setName(userTag + "-name")
                .setLastName(userTag + "-LastName")
                .setMail(userTag + "@mail.com")
                .build();
    }

    public static User UserRandomGenerator() {
        String userTag = randomStringGenerator();
        return new User.Builder()
                .setUserName(userTag)
                .setName(userTag + "-name")
                .setLastName(userTag + "-LastName")
                .setMail(userTag + "@mail.com")
                .build();
    }

    public static UserInputDTO UserInputDTORandomGenerator() {
        String userTag = randomStringGenerator();
        return new UserInputDTO.Builder()
                .setUserName(userTag)
                .setName(userTag + "-name")
                .setLastName(userTag + "-LastName")
                .setMail(userTag + "@mail.com")
                .build();
    }

    public List<User> userListGenerator() {
        return IntStream.rangeClosed(0, random.nextInt(8) + 1)
                .mapToObj(i -> UserRandomGenerator())
                .collect(Collectors.toList());
    }

}
