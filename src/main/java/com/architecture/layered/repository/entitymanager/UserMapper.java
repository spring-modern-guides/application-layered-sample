package com.architecture.layered.repository.entitymanager;

import com.architecture.layered.model.User;
import com.architecture.layered.repository.userview.UserView;

public class UserMapper {

    private UserMapper() {}

    static EntityManagerUserEntity toEntity(User user) {
        return new EntityManagerUserEntity(
                user.id(),
                user.name(),
                user.birthDate()
        );
    }

    static UserView toView(EntityManagerUserEntity entity) {
        return new UserView(
                entity.getId(),
                entity.getName(),
                entity.getBirthDate()
        );
    }

    static User toDomain(EntityManagerUserEntity entity) {
        return User.restore(
                entity.getId(),
                entity.getName(),
                entity.getBirthDate()
        );
    }

}

