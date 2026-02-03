package com.architecture.layered.repository.datajdbc;

import com.architecture.layered.model.User;
import com.architecture.layered.repository.userview.UserView;

public class UserJdbcMapper {

    static UserJdbcEntity toEntity(User user) {
        return new UserJdbcEntity(user.id(), user.name(), user.birthDate());
    }

    static User toDomain(UserJdbcEntity e) {
        return User.restore(e.id(), e.name(), e.birthDate());
    }

    static UserView toView(UserJdbcEntity e) {
        return new UserView(e.id(), e.name(), e.birthDate());
    }

    private UserJdbcMapper() {}
}
