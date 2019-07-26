package com.sos.facemash.utils;

import com.sos.facemash.DTO.MsgInputDTO;
import com.sos.facemash.entity.Msg;
import com.sos.facemash.entity.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MsgUtils extends CoreUtils {

    private static Msg ownMsgRandomGenerator(User owner) {
        return new Msg.Builder()
                .setId(random.nextLong())
                .setTitle(randomStringGenerator())
                .setBody(randomStringGenerator())
                .setDate(new Date(random.nextLong()))
                .setOwner(owner)
                .build();
    }

    private static Msg ownMsgRandomWithFilterGenerator(User owner, String common) {
        return new Msg.Builder()
                .setId(random.nextLong())
                .setTitle(common+"-"+randomStringGenerator())
                .setBody(common+"-"+randomStringGenerator())
                .setDate(new Date(random.nextLong()))
                .setOwner(owner)
                .build();
    }

    private static Msg msgRandomGeneratorWithDest(User owner, User destinatary) {
        return new Msg.Builder()
                .setId(random.nextLong())
                .setTitle(randomStringGenerator())
                .setBody(randomStringGenerator())
                .setDate(new Date(random.nextLong()))
                .setOwner(owner)
                .setDestination(destinatary)
                .build();
    }

    private static Msg msgRandomGenerator() {
        return new Msg.Builder()
                .setId(random.nextLong())
                .setTitle(randomStringGenerator())
                .setBody(randomStringGenerator())
                .setDate(new Date(random.nextLong()))
                .setOwner(UserUtils.UserRandomGenerator())
                .setDestination(UserUtils.UserRandomGenerator())
                .build();
    }

    public static MsgInputDTO msgInputDTORandomGenerator() {
        return new MsgInputDTO.Builder()
                .setTitle(randomStringGenerator())
                .setBody(randomStringGenerator())
                .setDate(new Date(random.nextLong()))
                .setDestination(UserUtils.UserRandomGenerator())
                .build();
    }

    public static List<Msg> msgRandomListGenerator() {
        return IntStream.rangeClosed(0, random.nextInt(8) + 1)
                .mapToObj(i -> msgRandomGenerator())
                .collect(Collectors.toList());
    }

    public static List<Msg> msgOfUserRandomListGenerator(User owner) {
        return IntStream.rangeClosed(0, random.nextInt(8) + 1)
                .mapToObj(i -> ownMsgRandomGenerator(owner))
                .collect(Collectors.toList());
    }

    public static List<Msg> msgOfUserAndFilterRandomListGenerator(User owner, String common) {
        return IntStream.rangeClosed(0, random.nextInt(8) + 1)
                .mapToObj(i -> ownMsgRandomWithFilterGenerator(owner, common))
                .collect(Collectors.toList());
    }

}
