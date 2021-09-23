package com.rvbenlg.clientservice.dtos;

import com.rvbenlg.clientservice.restmodels.responses.AlbumResponse;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 9030379281249767995L;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userId;
    private String encyptedPassword;
    private List<AlbumResponse> albums;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEncyptedPassword() {
        return encyptedPassword;
    }

    public void setEncyptedPassword(String encyptedPassword) {
        this.encyptedPassword = encyptedPassword;
    }

    public List<AlbumResponse> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumResponse> albums) {
        this.albums = albums;
    }
}
