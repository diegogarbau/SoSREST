package com.sos.facemash.service.imp;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.DTO.mapper.UserInputDTOToUser;
import com.sos.facemash.DTO.mapper.UserToUserDetailDTO;
import com.sos.facemash.DTO.mapper.UserToUserSummaryDTO;
import com.sos.facemash.core.Exceptions.UserNotFoundException;
import com.sos.facemash.core.Exceptions.WrongInputException;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.UserDAO;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private final int PHONE_LENGHT = 9;
    private UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UsersDTO getAllUsers(String filter) {
        Predicate<User> filterByUserName = user -> user.getUserName().contains(filter);
        return new UsersDTO().insertAll(userDAO.findAll()
                .stream()
                .filter(filterByUserName)
                .map(UserToUserSummaryDTO::map)
                .collect(Collectors.toList()));
    }

    @Override
    public UserDetailDTO getUser(String userName) {
        return UserToUserDetailDTO.map(userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDetailDTO createUser(UserInputDTO user) {
        if (!validInput(user)) throw new WrongInputException();
        User insertUser = UserInputDTOToUser.map((user));
        return saveUser(insertUser);

    }

    private UserDetailDTO saveUser(User user) {
        return UserToUserDetailDTO.map(userDAO.save(user));
    }


    private boolean validInput(UserInputDTO user) {
        return true;
    }

    @Override
    public UserDetailDTO modifyUser(String userName, UserInputDTO user) {
        if (!validInput(user)) throw new WrongInputException();
        User userToUpdate = userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new);
        return saveUser(updateUser(userToUpdate, UserInputDTOToUser.map((user))));
    }

    public void deleteUser(String userName) {

    }

    private User updateUser(User oldUser, User newUser) {
        Predicate<User> newMail = user -> (notNullOrBlank(user.getMail()));
        Predicate<User> newName = user -> (notNullOrBlank(user.getName()));
        Predicate<User> newLastName = user -> (notNullOrBlank(user.getLastName()));
        Predicate<User> newPhone = user -> (validPhone(user.getPhone()));
        BiConsumer<User, User> updatedMail = (a, b) -> a.setMail(b.getMail());
        BiConsumer<User, User> updatedName = (a, b) -> a.setName(b.getName());
        BiConsumer<User, User> updatedLastName = (a, b) -> a.setLastname(b.getLastName());
        BiConsumer<User, User> updatedPhone = (a, b) -> a.setPhone(b.getPhone());

        BiConsumer<User, User> userUserBiConsumer = this::accept;
        userUserBiConsumer.accept(oldUser, newUser);

        return oldUser;
    }

    private void Update(Predicate predicate, BiConsumer consumer) {
//        doSomething(someValue, this::customConsumerMethodThrowingAnException);

    }


//    interface Predicate{
//        boolean check(User user);
//    }

    private boolean notNullOrBlank(String s) {
        return (s != null && s != "");
    }

    private boolean validPhone(int s) {
        return (String.valueOf(s).length() == PHONE_LENGHT);
    }

    private void accept(User a, User b) {
        if (notNullOrBlank(b.getMail())) {
            a.setMail(b.getMail());
        }
    }

    @FunctionalInterface
    public interface ThrowingConsumer<T> {
        void accept(T t);
    }

//    public <T, R> void doSomething(T value, ThrowingConsumer<T, R> consumer) {
//        // ...
//    }
//    BiFunction : salaries.replaceAll((name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 10000);
//BiFunction<User, User, User> replace = (a, b) -> a.setMail(b.getMail());

}
