package com.architecture.layered.repository.jdbc;

import com.architecture.layered.repository.UserReadRepository;
import com.architecture.layered.repository.userview.UserView;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class JdbcUserReadRepositoryImpl implements UserReadRepository {

    private final JdbcClient jdbc;
    private static final String TABLE = "users";

    public JdbcUserReadRepositoryImpl(JdbcClient jdbc) { this.jdbc = jdbc;}

    private interface Sql {

        String FIND_BY_ID = "SELECT id, name, birth_date FROM users WHERE id = :id";
        String FIND_ALL = "SELECT id, name, birth_date FROM users";
        String FIND_BY_NAME_PREFIX = "SELECT id, name, birth_date FROM users WHERE name LIKE :name";

    }

    public Optional<UserView> findById(UUID id) {
        return jdbc.sql(Sql.FIND_BY_ID).param("id", id).query(UserView.class).optional();
    }

    public List<UserView> findAll() {
        return jdbc.sql(Sql.FIND_ALL).query(UserView.class).list();
    }

    public List<UserView> findByNameStartingWith(String prefix) {
        return jdbc.sql(Sql.FIND_BY_NAME_PREFIX).param("name", prefix + "%").query(UserView.class).list();
    }

}
