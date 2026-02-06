package com.architecture.layered.model;

import java.time.Clock;
import java.util.Objects;
import java.util.UUID;
import java.time.LocalDate;
import java.util.regex.Pattern;

public final class User {

    private final UUID id;
    private final String name;
    private final LocalDate birthDate;

    private static final Clock CLOCK = Clock.systemDefaultZone();
    private static final Pattern ALLOWED = Pattern.compile("^[\\p{L}\\s\\-'.,]+$");


    private User(UUID id, String name, LocalDate birthDate) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.name = validateName(name);
        this.birthDate = validateBirthDate(birthDate);
    }

    public static User create(String name, LocalDate birthDate) {
        return new User(UUID.randomUUID(), name, birthDate);
    }

    public static User restore(UUID id, String name, LocalDate birthDate) {
        return new User(id, name, birthDate);
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new IllegalArgumentException(message);
    }

    private static String validateName(String name) {
        String trimmed = Objects.requireNonNull(name, "Name required").trim();
        require(!trimmed.isEmpty(), "Name cannot be empty");
        require(trimmed.length() >= 2 && trimmed.length() <= 20, "Name must be 2-20 chars");
        require(ALLOWED.matcher(trimmed).matches(), "Invalid characters in name");
        return trimmed;
    }

    private static LocalDate validateBirthDate(LocalDate birthDate) {
        Objects.requireNonNull(birthDate, "Birth date cannot be null");
        LocalDate today = LocalDate.now(CLOCK);
        require(!birthDate.isAfter(today), "Birth date cannot be in the future");
        return birthDate;
    }

    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    public UUID id() { return id;}
    public String name() { return name;}
    public LocalDate birthDate() { return birthDate;}

    @Override
    public boolean equals(Object o) {
        return o instanceof User other && id.equals(other.id);
    }

    @Override
    public int hashCode() { return id.hashCode();}

}
