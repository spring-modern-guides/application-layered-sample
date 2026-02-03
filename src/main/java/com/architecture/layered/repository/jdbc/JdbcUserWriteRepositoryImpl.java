package com.architecture.layered.repository.jdbc;


import com.architecture.layered.model.User;
import com.architecture.layered.repository.UserWriteRepository;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.Objects;
import java.util.UUID;

public final class JdbcUserWriteRepositoryImpl implements UserWriteRepository {

    private final JdbcClient jdbc;

    public JdbcUserWriteRepositoryImpl(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    private interface Sql {

        String INSERT = "INSERT INTO users (id, name, birth_date) VALUES (:id, :name, :birthDate)";
        String UPDATE = "UPDATE users SET name = :name, birth_date = :birthDate WHERE id = :id";
        String DELETE = "DELETE FROM users WHERE id = :id";

    }

    @Override
    public void save(User user) {
        Objects.requireNonNull(user);

        int updated = jdbc
                .sql(Sql.UPDATE)
                .param("id", user.id())
                .param("name", user.name())
                .param("birthDate", user.birthDate())
                .update();

        if (updated == 0) {
            jdbc
                    .sql(Sql.INSERT)
                    .param("id", user.id())
                    .param("name", user.name())
                    .param("birthDate", user.birthDate())
                    .update();
        }
    }

    @Override
    public void deleteById(UUID id) {
        jdbc.sql(Sql.DELETE).param("id", id).update();
    }

}
