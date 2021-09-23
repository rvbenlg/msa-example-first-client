package com.rvbenlg.clientservice.services.impl;


import com.rvbenlg.clientservice.dtos.UserDto;
import com.rvbenlg.clientservice.entities.User;
import com.rvbenlg.clientservice.repositories.UserRepository;
import com.rvbenlg.clientservice.restmodels.requests.CreateUserRequest;
import com.rvbenlg.clientservice.restmodels.responses.AlbumResponse;
import com.rvbenlg.clientservice.restmodels.responses.CreateUserResponse;
import com.rvbenlg.clientservice.services.UserService;
import com.rvbenlg.clientservice.utils.Mapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.restTemplate = restTemplate;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        UserDto userDto = Mapper.mapCreateUserRequestToUserDto(createUserRequest);
        userDto.setEncyptedPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        userDto.setUserId(UUID.randomUUID().toString());
        User user = Mapper.mapUserDtoToUser(userDto);
        userRepository.save(user);
        return Mapper.mapUserDtoToCreateUserResponse(userDto);
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new ModelMapper().map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }
        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        String albumsUrl = String.format("http://second-client-ws/users/%s/albums", userId);
        logger.info("Before calling second client service");
        ResponseEntity<List<AlbumResponse>> albumsResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponse>>() {
        });
        logger.info("After calling second client service");
        List<AlbumResponse> albums = albumsResponse.getBody();
        userDto.setAlbums(albums);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncyptedPassword(),
                true, true, true, true, new ArrayList<>());
    }
}
