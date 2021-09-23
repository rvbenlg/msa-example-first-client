package com.rvbenlg.clientservice.utils;

import com.rvbenlg.clientservice.dtos.UserDto;
import com.rvbenlg.clientservice.entities.User;
import com.rvbenlg.clientservice.restmodels.requests.CreateUserRequest;
import com.rvbenlg.clientservice.restmodels.responses.CreateUserResponse;

public class Mapper {

    public static User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUserId(userDto.getUserId());
        user.setEncyptedPassword(userDto.getEncyptedPassword());
        return user;
    }

    public static UserDto mapCreateUserRequestToUserDto(CreateUserRequest createUserRequest) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(createUserRequest.getFirstName());
        userDto.setLastName(createUserRequest.getLastName());
        userDto.setEmail(createUserRequest.getEmail());
        userDto.setPassword(createUserRequest.getPassword());
        return userDto;
    }

    public static CreateUserResponse mapUserDtoToCreateUserResponse(UserDto userDto) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setFirstName(userDto.getFirstName());
        createUserResponse.setLastName(userDto.getLastName());
        createUserResponse.setEmail(userDto.getEmail());
        createUserResponse.setUserId(userDto.getUserId());
        return createUserResponse;
    }

}
