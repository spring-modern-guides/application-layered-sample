package com.architecture.layered.controller.rest;

import com.architecture.layered.controller.rest.dto.ChangeProfileRequest;
import com.architecture.layered.controller.rest.dto.CreateUserRequest;
import com.architecture.layered.service.UserCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public final class UserCommandController {

    private final UserCommandService service;

    public UserCommandController(UserCommandService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody CreateUserRequest request) {
        UUID id = service.register(request.name(), request.birthDate());

        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> changeProfile(
            @PathVariable UUID id,
            @RequestBody ChangeProfileRequest request
    ) {
        service.changeProfile(id, request.name(), request.birthDate());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

