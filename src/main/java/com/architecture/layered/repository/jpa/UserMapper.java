package com.architecture.layered.repository.jpa;

import com.architecture.layered.model.User;
import com.architecture.layered.repository.userview.UserView;

public class UserMapper {

    private UserMapper() {}

    static UserEntity toEntity(User user) {
        return new UserEntity(user.id(), user.name(), user.birthDate());
    }

    static User toDomain(UserEntity entity) {
        return User.restore(entity.getId(), entity.getName(), entity.getBirthDate());
    }

    static UserView toView(UserEntity entity) {
        return new UserView(entity.getId(), entity.getName(), entity.getBirthDate());
    }

}

