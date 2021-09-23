package com.rvbenlg.clientservice.services;

import com.rvbenlg.clientservice.dtos.UserDto;
import com.rvbenlg.clientservice.restmodels.requests.CreateUserRequest;
import com.rvbenlg.clientservice.restmodels.responses.CreateUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    UserDto getUserDetailsByEmail(String email);

    UserDto getUserById(String userId);

}
