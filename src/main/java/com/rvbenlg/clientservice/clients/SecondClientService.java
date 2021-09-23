package com.rvbenlg.clientservice.clients;

import com.rvbenlg.clientservice.restmodels.responses.AlbumResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "second-client-ws")
public interface SecondClientService {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponse> getAlbums(@PathVariable String id);

}
