package com.architecture.layered.repository.datajdbc;

import com.architecture.layered.model.User;
import com.architecture.layered.repository.UserWriteRepository;
import org.springframework.context.annotation.Profile;

import java.util.UUID;

@Profile("data-jdbc")
public class DataJdbcUserWriteRepositoryImpl implements UserWriteRepository {

    private final DataJdbcUserRepository repo;

    public DataJdbcUserWriteRepositoryImpl(DataJdbcUserRepository repo) { this.repo = repo;}

    @Override
    public void save(User user) { repo.save(UserJdbcMapper.toEntity(user));}

    @Override
    public void deleteById(UUID id) { repo.deleteById(id);}

}
