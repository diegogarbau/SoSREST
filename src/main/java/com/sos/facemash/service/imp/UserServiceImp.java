package com.sos.facemash.service.imp;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.DTO.mapper.UserInputDTOToUser;
import com.sos.facemash.DTO.mapper.UserToUserDetailDTO;
import com.sos.facemash.DTO.mapper.UserToUserSummaryDTO;
import com.sos.facemash.core.Exceptions.AlreadyFriendsException;
import com.sos.facemash.core.Exceptions.DuplicatedException;
import com.sos.facemash.core.Exceptions.NotFriendsException;
import com.sos.facemash.core.Exceptions.UserNotFoundException;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.UserDAO;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
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

    public User getUser(String userName) {
        return userDAO.findByUserName(userName).orElseThrow(()
                -> new UserNotFoundException("El usuario no figura en la base de datos"));
    }

    @Override
    public UserDetailDTO getUserDetail(String userName) {
        return UserToUserDetailDTO.map(getUser(userName));
    }

    @Override
    public UserDetailDTO createUser(UserInputDTO user) {
        if (userDAO.findByUserName(user.getUserName()).isPresent())
            throw new DuplicatedException("Ya Existe ese usuario");
        User insertUser = UserInputDTOToUser.map((user));
        return saveUser(insertUser);

    }

    @Override
    public UserDetailDTO modifyUser(String userName, UserInputDTO user) {
        User userToUpdate = userDAO.findByUserName(userName).orElseThrow(()
                -> new UserNotFoundException("El usuario no figura en la base de datos"));
        return saveUser(updateUser(userToUpdate, UserInputDTOToUser.map((user))));
    }

    private UserDetailDTO saveUser(User user) {
        return UserToUserDetailDTO.map(userDAO.save(user));
    }

    private User updateUser(User oldUser, User newUser) {
        return oldUser.updateUser(newUser);
    }

    public void deleteUser(String userName) {
        userDAO.delete(userDAO.findByUserName(userName).orElseThrow(()
                -> new UserNotFoundException("El usuario no figura en la base de datos")));
    }

    @Override
    public UsersDTO addFriend(String userName, String friendUserName) {
        User user = userDAO.findByUserName(userName).orElseThrow(()
                -> new UserNotFoundException("El usuario no figura en la base de datos"));
        User newFriend = userDAO.findByUserName(friendUserName).orElseThrow(()
                -> new UserNotFoundException("El usuario no figura en la base de datos"));
        return getFriendsList(makeFriends(user, newFriend));
    }

    @Override
    public UsersDTO deleteFriend(String userName, String friendUserName) {
        User user = userDAO.findByUserName(userName).orElseThrow(()
                -> new UserNotFoundException("El usuario no figura en la base de datos"));
        User friend = userDAO.findByUserName(friendUserName).orElseThrow(()
                -> new UserNotFoundException("El usuario no figura en la base de datos"));
        if (!user.getFriends().contains(friend))
            throw new NotFriendsException("Esos usuarios no son amigos");
        user.getFriends().remove(friend);
        friend.getFriendOf().remove(user);
        return getFriendsList(userDAO.save(user));
    }

    @Override
    public UsersDTO getFriends(String userName) {
        return getFriendsList(userDAO.findByUserName(userName).orElseThrow(()
                -> new UserNotFoundException("El usuario no figura en la base de datos")));
    }

    private User makeFriends(User user, User newFriend) {
        if (user.getFriends().contains(newFriend))
            throw new AlreadyFriendsException("Ya son amigos");
        user.getFriends().add(newFriend);
        newFriend.getFriendOf().add(user);
        userDAO.save(newFriend);
        return userDAO.save(user);
    }

    private UsersDTO getFriendsList(User user) {
        return new UsersDTO().insertAll(user.getFriends()
                .stream()
                .map(UserToUserSummaryDTO::map)
                .collect(Collectors.toList()));
    }
}
