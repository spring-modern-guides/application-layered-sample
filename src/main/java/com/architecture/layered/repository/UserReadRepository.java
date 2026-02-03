package com.architecture.layered.repository;

import com.architecture.layered.repository.userview.UserView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserReadRepository {

    Optional<UserView> findById(UUID id);

    List<UserView> findAll();

    List<UserView> findByNameStartingWith(String prefix);

}

