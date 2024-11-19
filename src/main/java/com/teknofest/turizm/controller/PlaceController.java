package com.teknofest.turizm.controller;

import com.teknofest.turizm.model.Place;
import com.teknofest.turizm.response.ApiResponse;
import com.teknofest.turizm.response.AuthenticationResponse;
import com.teknofest.turizm.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/place")

public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Place>> save(@RequestBody Place place) {
        var result = placeService.savePlace(place);
        var response = new ApiResponse.Builder<Place>()
                .success(true)
                .message("Kayıt başarılı")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}


