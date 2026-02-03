package com.architecture.layered.config;

import com.architecture.layered.repository.datajdbc.DataJdbcUserReadRepositoryImpl;
import com.architecture.layered.repository.datajdbc.DataJdbcUserRepository;
import com.architecture.layered.repository.datajdbc.DataJdbcUserWriteRepositoryImpl;
import com.architecture.layered.repository.entitymanager.EmUserReadRepositoryImpl;
import com.architecture.layered.repository.entitymanager.EmUserWriteRepositoryImpl;
import com.architecture.layered.repository.jooq.JooqUserReadRepositoryImpl;
import com.architecture.layered.repository.jooq.JooqUserWriteRepositoryImpl;
import com.architecture.layered.repository.jpa.JpaUserReadRepositoryImpl;
import com.architecture.layered.repository.jpa.JpaUserRepository;
import com.architecture.layered.repository.jpa.JpaUserWriteRepositoryImpl;
import com.architecture.layered.service.UserCommandService;
import com.architecture.layered.service.UserQueryService;
import com.architecture.layered.repository.jdbc.JdbcUserReadRepositoryImpl;
import com.architecture.layered.repository.jdbc.JdbcUserWriteRepositoryImpl;
import com.architecture.layered.repository.UserReadRepository;
import com.architecture.layered.repository.UserWriteRepository;
import jakarta.persistence.EntityManager;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
public class UserConfig {

    @Bean
    @Profile("jdbc")
    UserWriteRepository jdbcWriteRepo(JdbcClient jdbc) {
        return new JdbcUserWriteRepositoryImpl(jdbc);
    }

    @Bean
    @Profile("jooq")
    UserWriteRepository jooqWriteRepo(DSLContext dsl) {
        return new JooqUserWriteRepositoryImpl(dsl);
    }

    @Bean
    @Profile("entity-manager")
    public UserWriteRepository entityManagerWriteRepo(EntityManager em) {
        return new EmUserWriteRepositoryImpl(em);
    }

    @Bean
    @Profile("jpa")
    public UserWriteRepository jpaWriteRepo(JpaUserRepository jpa) {
        return new JpaUserWriteRepositoryImpl(jpa);
    }

    @Bean
    @Profile("data-jdbc")
    public UserWriteRepository dataJdbcWriteRepo(DataJdbcUserRepository repo) {
        return new DataJdbcUserWriteRepositoryImpl(repo);
    }

    @Bean
    UserCommandService userCommandService(UserWriteRepository userWritePort) {
        return new UserCommandService(userWritePort);
    }

    @Bean
    @Profile("jdbc")
    UserReadRepository jdbcReadRepo(JdbcClient jdbc) {
        return new JdbcUserReadRepositoryImpl(jdbc);
    }

    @Bean
    @Profile("jooq")
    UserReadRepository jooqReadRepo(DSLContext dsl) {
        return new JooqUserReadRepositoryImpl(dsl);
    }

    @Bean
    @Profile("entity-manager")
    public UserReadRepository entityManagerReadRepo(EntityManager em) {
        return new EmUserReadRepositoryImpl(em);
    }

    @Bean
    @Profile("jpa")
    public UserReadRepository jpaReadRepo(JpaUserRepository jpa) {
        return new JpaUserReadRepositoryImpl(jpa);
    }

    @Bean
    @Profile("data-jdbc")
    public UserReadRepository dataJdbcReadRepo(DataJdbcUserRepository repo) {
        return new DataJdbcUserReadRepositoryImpl(repo);
    }

    @Bean
    UserQueryService userQueryService(UserReadRepository userReadPort) {
        return new UserQueryService(userReadPort);
    }

}

@Component
class ProfileCheck {

    public ProfileCheck(Environment env) {
        System.out.println("Active profiles: " + Arrays.toString(env.getActiveProfiles()));
    }
}
