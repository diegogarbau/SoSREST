package com.sos.facemash.persistance;


import com.sos.facemash.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends CrudRepository <User, String> {

    Optional<User> findByUser(String userName);

    List<User> findAll();
}
