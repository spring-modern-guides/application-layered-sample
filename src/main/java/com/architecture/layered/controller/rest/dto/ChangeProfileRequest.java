package com.architecture.layered.controller.rest.dto;

import java.time.LocalDate;

public record ChangeProfileRequest(String name, LocalDate birthDate) {}
