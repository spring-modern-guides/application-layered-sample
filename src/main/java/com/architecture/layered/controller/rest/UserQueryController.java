package com.architecture.layered.controller.rest;

import com.architecture.layered.service.UserQueryService;
import com.architecture.layered.repository.userview.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public final class UserQueryController {

    private final UserQueryService service;

    public UserQueryController(UserQueryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserView getDetails(@PathVariable UUID id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<UserView> list(@RequestParam(required = false) String namePrefix){
        if (namePrefix == null) { return service.findAll();}
        return service.findByNameStartingWith(namePrefix);
    }

}

@RestController
class PingController {

    @GetMapping("/ping")
    String ping() {
        return "pong";
    }
}
