package com.architecture.layered.repository.userview;

import java.util.UUID;
import java.time.LocalDate;

public record UserView(UUID id, String name, LocalDate birthDate) {}
