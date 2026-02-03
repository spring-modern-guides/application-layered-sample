package com.architecture.layered.repository.datajdbc;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DataJdbcUserRepository extends CrudRepository<UserJdbcEntity, UUID> {

    List<UserJdbcEntity> findByNameStartingWith(String prefix);

}
