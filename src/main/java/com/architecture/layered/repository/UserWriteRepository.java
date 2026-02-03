package com.architecture.layered.repository;

import com.architecture.layered.model.User;

import java.util.UUID;

public interface UserWriteRepository {

    void save(User user);
    void deleteById(UUID id);

}
