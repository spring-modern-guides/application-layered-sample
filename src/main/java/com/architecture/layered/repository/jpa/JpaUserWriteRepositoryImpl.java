package com.architecture.layered.repository.jpa;

import com.architecture.layered.model.User;
import com.architecture.layered.repository.UserWriteRepository;
import org.springframework.context.annotation.Profile;

import java.util.UUID;


@Profile("jpa")
public class JpaUserWriteRepositoryImpl implements UserWriteRepository {

    private final JpaUserRepository jpa;

    public JpaUserWriteRepositoryImpl(JpaUserRepository jpa) { this.jpa = jpa;}

    @Override
    public void save(User user) { jpa.save(UserMapper.toEntity(user));}

    @Override
    public void deleteById(UUID id) { jpa.deleteById(id);}

}
