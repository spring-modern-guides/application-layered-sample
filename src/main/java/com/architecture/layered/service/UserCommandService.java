package com.architecture.layered.service;

import com.architecture.layered.model.User;
import com.architecture.layered.repository.UserWriteRepository;

import java.time.LocalDate;
import java.util.UUID;

public final class UserCommandService {

    private final UserWriteRepository users;

    public UserCommandService(UserWriteRepository users) {
        this.users = users;
    }

    public UUID register(String name, LocalDate birthDate) {
        User user = User.create(name, birthDate);
        users.save(user);
        return user.id();
    }

    public void changeProfile(UUID id, String name, LocalDate birthDate) {
        User user = User.restore(id, name, birthDate);
        users.save(user);
    }

    public void delete(UUID id) {
        users.deleteById(id);
    }

}
