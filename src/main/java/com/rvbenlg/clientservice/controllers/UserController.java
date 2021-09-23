package com.rvbenlg.clientservice.controllers;

import com.rvbenlg.clientservice.restmodels.requests.CreateUserRequest;
import com.rvbenlg.clientservice.restmodels.responses.CreateUserResponse;
import com.rvbenlg.clientservice.restmodels.responses.GetUserDetailsResponse;
import com.rvbenlg.clientservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = userService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUserDetails(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(userService.getUserById(userId), GetUserDetailsResponse.class));
    }

}
