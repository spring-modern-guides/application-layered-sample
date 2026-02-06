package com.architecture.layered.model;

import java.time.LocalDate;
import java.util.UUID;

public record User(UUID id, String name, LocalDate birthDate) {

    public static User create(String name, LocalDate birthDate) {
        return new User(UUID.randomUUID(), name, birthDate);
    }

    public static User restore(UUID id, String name, LocalDate birthDate) {
        return new User(id, name, birthDate);
    }

}
