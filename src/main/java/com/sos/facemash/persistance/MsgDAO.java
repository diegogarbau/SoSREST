package com.sos.facemash.persistance;


import com.sos.facemash.entity.Msg;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MsgDAO extends CrudRepository<Msg, Long> {

    Optional<Msg> findByUser(Long id);

    List<Msg> findAllByUser(String userName);

    List<Msg> findAll();
}
