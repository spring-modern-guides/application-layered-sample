package com.architecture.layered.repository.datajdbc;

import com.architecture.layered.repository.UserReadRepository;
import com.architecture.layered.repository.userview.UserView;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("data-jdbc")
public class DataJdbcUserReadRepositoryImpl implements UserReadRepository {

    private final DataJdbcUserRepository repo;

    public DataJdbcUserReadRepositoryImpl(DataJdbcUserRepository repo) { this.repo = repo;}

    @Override
    public Optional<UserView> findById(UUID id) {
        return repo.findById(id).map(UserJdbcMapper::toView);
    }

    @Override
    public List<UserView> findAll() {
        return ((List<?>) repo.findAll())
                .stream()
                .map(UserJdbcEntity.class::cast)
                .map(UserJdbcMapper::toView)
                .toList();
    }

    @Override
    public List<UserView> findByNameStartingWith(String prefix) {
        return repo.findByNameStartingWith(prefix)
                .stream()
                .map(UserJdbcMapper::toView)
                .toList();
    }

}
