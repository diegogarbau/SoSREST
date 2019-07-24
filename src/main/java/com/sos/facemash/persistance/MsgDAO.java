package com.sos.facemash.persistance;


import com.sos.facemash.entity.Msg;
import com.sos.facemash.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MsgDAO extends CrudRepository<Msg, Long> {

    Optional<Msg> findByIdAndOwner(Long id,User user);

    List<Msg> findAllByOwner(User user);

    List<Msg> findAll();
}
