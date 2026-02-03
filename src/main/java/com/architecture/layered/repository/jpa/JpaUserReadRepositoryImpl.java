package com.architecture.layered.repository.jpa;

import com.architecture.layered.repository.UserReadRepository;
import com.architecture.layered.repository.userview.UserView;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Profile("jpa")
public class JpaUserReadRepositoryImpl implements UserReadRepository {

    private final JpaUserRepository jpa;

    public JpaUserReadRepositoryImpl(JpaUserRepository jpa) { this.jpa = jpa;}

    @Override
    public Optional<UserView> findById(UUID id) {
        return jpa.findById(id).map(UserMapper::toView);
    }

    @Override
    public List<UserView> findAll() {
        return jpa.findAll().stream().map(UserMapper::toView).toList();
    }

    @Override
    public List<UserView> findByNameStartingWith(String prefix) {
        return jpa.findByNameStartingWithIgnoreCase(prefix)
                .stream()
                .map(UserMapper::toView)
                .toList();
    }

}