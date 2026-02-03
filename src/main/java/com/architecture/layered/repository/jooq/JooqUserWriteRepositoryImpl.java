package com.architecture.layered.repository.jooq;

import com.architecture.layered.model.User;
import com.architecture.layered.repository.UserWriteRepository;
import org.jooq.DSLContext;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static org.jooq.impl.DSL.*;

public final class JooqUserWriteRepositoryImpl implements UserWriteRepository {

    private final DSLContext dsl;

    public JooqUserWriteRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public void save(User user) {
        Objects.requireNonNull(user, "User must not be null");

        int updated = dsl
                .update(table("users"))
                .set(field("name", String.class), user.name())
                .set(field("birth_date", LocalDate.class), user.birthDate())
                .where(field("id", UUID.class).eq(user.id()))
                .execute();

        if (updated == 0) {
            dsl.insertInto(table("users"))
                    .set(field("id", UUID.class), user.id())
                    .set(field("name", String.class), user.name())
                    .set(field("birth_date", LocalDate.class), user.birthDate())
                    .execute();
        }
    }

    @Override
    public void deleteById(UUID id) {
        Objects.requireNonNull(id, "Id must not be null");

        dsl.deleteFrom(table("users"))
                .where(field("id", UUID.class).eq(id))
                .execute();
    }

}
