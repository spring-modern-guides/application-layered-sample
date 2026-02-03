package com.architecture.layered.controller.rest.dto;

import java.time.LocalDate;

public record CreateUserRequest(String name, LocalDate birthDate) {}
