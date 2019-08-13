package com.sos.facemash.service.impl;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.DTO.mapper.UserInputDTOToUser;
import com.sos.facemash.DTO.mapper.UserToUserDetailDTO;
import com.sos.facemash.DTO.mapper.UserToUserSummaryDTO;
import com.sos.facemash.core.exceptions.usersExceptions.*;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.UserDAO;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

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

    public User getUserOptional(String userName) {
        return userDAO.findByUserName(userName).orElse(null);
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
        if (!user.getUserName().equals(userName)) {
            checkUserNameUsed(user.getUserName());
        }
        if (!user.getMail().equals(userToUpdate.getMail())) {
            checkMailUsed(user.getMail());
        }
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

    private void checkUserNameUsed(String userName) {
        if (userDAO.existsByUserName(userName))
            throw new UserNameAlreadyUsingException("Ese nombre de usuario ya se encuentra en uso");
    }

    private void checkMailUsed(String mail) {
        if (userDAO.existsByMail(mail)) throw new MailAlreadyUsingException("Ese correo ya se encuentra en uso");
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
        return new UsersDTO(
                Stream
                        .concat(user.getFriends().stream(),
                                user.getFriendOf().stream())
                        .distinct()
                        .map(UserToUserSummaryDTO::map)
                        .collect(Collectors.toList()));
    }
}
