package com.architecture.layered.repository.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private UUID id;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    protected UserEntity() {}

    public UserEntity(UUID id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public UUID getId() { return id;}
    public String getName() { return name;}
    public LocalDate getBirthDate() { return birthDate;}

}

