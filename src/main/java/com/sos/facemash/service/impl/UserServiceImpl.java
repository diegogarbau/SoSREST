package com.sos.facemash.service.impl;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.DTO.mapper.UserInputDTOToUser;
import com.sos.facemash.DTO.mapper.UserToUserDetailDTO;
import com.sos.facemash.DTO.mapper.UserToUserSummaryDTO;
import com.sos.facemash.core.exceptions.usersExceptions.AlreadyFriendsException;
import com.sos.facemash.core.exceptions.usersExceptions.DuplicatedUserException;
import com.sos.facemash.core.exceptions.usersExceptions.NotFriendsException;
import com.sos.facemash.core.exceptions.usersExceptions.UserNotFoundException;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.UserDAO;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UsersDTO getAllUsers() {
        return new UsersDTO(userDAO.findAll()
                .stream()
                .map(UserToUserSummaryDTO::map)
                .collect(Collectors.toList()));
    }

    @Override
    public UsersDTO getAllUsers(String filter) {
        return new UsersDTO(userDAO.findAll()
                .stream()
                .filter(user -> user.getUserName().contains(filter))
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
            throw new DuplicatedUserException("Ya Existe ese usuario");
        return saveUser(UserInputDTOToUser.map((user)));

    }

    @Override
    public UserDetailDTO modifyUser(String userName, UserInputDTO user) {
        User userToUpdate = getUser(userName);
        return saveUser(updateUser(userToUpdate, UserInputDTOToUser.map((user))));
    }

    public UserDetailDTO saveUser(User user) {
        return UserToUserDetailDTO.map(userDAO.save(user));
    }

    private User updateUser(User oldUser, User newUser) {
        return oldUser.updateUser(newUser);
    }

    public void deleteUser(String userName) {
        userDAO.delete(getUser(userName));
    }

    @Override
    public UsersDTO addFriend(String userName, String friendUserName) {
        return getFriendsList(makeFriends(getUser(userName), getUser(friendUserName)));
    }

    @Override
    public UsersDTO deleteFriend(String userName, String friendUserName) {
        User user = getUser(userName);
        User friend = getUser(friendUserName);
        if (!user.getFriends().contains(friend))
            throw new NotFriendsException("Esos usuarios no son amigos");
        user.getFriends().remove(friend);
        friend.getFriendOf().remove(user);
        return getFriendsList(userDAO.save(user));
    }

    @Override
    public UsersDTO getFriends(String userName) {
        return getFriendsList(getUser(userName));
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
        return new UsersDTO(user.getFriends()
                .stream()
                .map(UserToUserSummaryDTO::map)
                .collect(Collectors.toList()));
    }
}
