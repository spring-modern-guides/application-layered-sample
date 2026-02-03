package com.architecture.layered.repository.jooq;

import com.architecture.layered.repository.UserReadRepository;
import com.architecture.layered.repository.userview.UserView;
import org.jooq.DSLContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.*;

public class JooqUserReadRepositoryImpl implements UserReadRepository {

    private final DSLContext dsl;

    public JooqUserReadRepositoryImpl(DSLContext dsl) { this.dsl = dsl;}

    @Override
    public Optional<UserView> findById(UUID id) {
        return dsl
                .select(
                        field("id", UUID.class),
                        field("name", String.class),
                        field("birth_date", LocalDate.class)
                )
                .from(table("users"))
                .where(field("id").eq(id))
                .fetchOptionalInto(UserView.class);
    }

    @Override
    public List<UserView> findAll() {
        return dsl
                .select(
                        field("id", UUID.class),
                        field("name", String.class),
                        field("birth_date", LocalDate.class)
                )
                .from(table("users"))
                .orderBy(field("name"))
                .fetchInto(UserView.class);
    }

    @Override
    public List<UserView> findByNameStartingWith(String name) {
        return dsl
                .select(
                        field("id", UUID.class),
                        field("name", String.class),
                        field("birth_date", LocalDate.class)
                )
                .from(table("users"))
                .where(field("name").like(name + "%"))
                .orderBy(field("name"))
                .fetchInto(UserView.class);
    }

}
