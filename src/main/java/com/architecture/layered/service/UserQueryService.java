package com.architecture.layered.service;

import com.architecture.layered.repository.UserReadRepository;
import com.architecture.layered.repository.userview.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class UserQueryService {

    private final UserReadRepository users;
    private static final Logger log = LoggerFactory.getLogger(UserQueryService.class);


    public UserQueryService(UserReadRepository users) {
        // ЛОГ ПРИ СТАРТЕ КОНТЕКСТА
        log.info("Repo class = {}", users.getClass());
        this.users = users;}

    public Optional<UserView> findById(UUID id) { return users.findById(id);}

    public List<UserView> findAll() {
        return users.findAll();
    }

    public List<UserView> findByNameStartingWith(String prefix) {
        return users.findByNameStartingWith(prefix);
    }

}
