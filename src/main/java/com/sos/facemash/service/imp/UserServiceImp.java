package com.sos.facemash.service.imp;

import com.sos.facemash.DTO.UserDetailDTO;
import com.sos.facemash.DTO.UserInputDTO;
import com.sos.facemash.DTO.UsersDTO;
import com.sos.facemash.DTO.mapper.UserInputDTOToUser;
import com.sos.facemash.DTO.mapper.UserToUserDetailDTO;
import com.sos.facemash.DTO.mapper.UserToUserSummaryDTO;
import com.sos.facemash.core.Exceptions.UserNotFoundException;
import com.sos.facemash.entity.User;
import com.sos.facemash.persistance.UserDAO;
import com.sos.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public UserDetailDTO getUser(String userName) {
        return UserToUserDetailDTO.map(userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDetailDTO createUser(UserInputDTO user) {
        User insertUser = UserInputDTOToUser.map((user));
        return saveUser(insertUser);

    }

    @Override
    public UserDetailDTO modifyUser(String userName, UserInputDTO user) {
        User userToUpdate = userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new);
        return saveUser(updateUser(userToUpdate, UserInputDTOToUser.map((user))));
    }

    private UserDetailDTO saveUser(User user) {
        return UserToUserDetailDTO.map(userDAO.save(user));
    }

    private User updateUser(User oldUser, User newUser) {
        return oldUser.updateUser(newUser);
    }

    public void deleteUser(String userName) {
        userDAO.delete(userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UsersDTO addFriend(String userName, String friendUserName) {
        User user = userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new);
        makeFriends(user, friendUserName);
        return getFriends(userDAO.save(user));
    }

    @Override
    public UsersDTO addFriends(String userName, List<String> friendsList) {
        User user = userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new);
        friendsList.forEach(friend -> makeFriends(user, friend));
        return getFriends(userDAO.save(user));
    }

    @Override
    public UsersDTO deleteFriend(String userName, String friendUserName) {
        User user = userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new);
        User friend = userDAO.findByUser(userName).orElseThrow(UserNotFoundException::new);
        user.getFriends().remove(friend);
        return getFriends(userDAO.save(user));
    }

    private void makeFriends(User user, String friendUserName) {
        User newFriend = userDAO.findByUser(friendUserName).orElseThrow(UserNotFoundException::new);
        if (!user.getFriends().contains(newFriend))
            user.getFriends().add(newFriend);
    }
    private UsersDTO getFriends(User user) {
        return new UsersDTO().insertAll(user.getFriends()
                .stream()
                .map(UserToUserSummaryDTO::map)
                .collect(Collectors.toList()));
    }
}
