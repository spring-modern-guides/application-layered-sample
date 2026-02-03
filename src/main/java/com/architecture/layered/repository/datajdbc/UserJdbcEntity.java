package com.architecture.layered.repository.datajdbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("USERS")
public record UserJdbcEntity(@Id UUID id, String name, LocalDate birthDate) {}
