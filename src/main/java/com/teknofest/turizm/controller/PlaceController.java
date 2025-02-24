package com.teknofest.turizm.controller;

import com.teknofest.turizm.model.Place;
import com.teknofest.turizm.model.ZoomLevel;
import com.teknofest.turizm.response.ApiResponse;
import com.teknofest.turizm.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Place>> updatePlace(@PathVariable Long id, @RequestBody Place uplace) {
        var result = placeService.updatePlace(id, uplace);
        var response = new ApiResponse.Builder<Place>()
                .success(true)
                .message("Kayıt güncellendi")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        var response = new ApiResponse.Builder<Void>()
                .success(true)
                .message("Kayıt Silindi")
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse<Place>> getPlaceById(@PathVariable Long id) {
        var result = placeService.getPlaceById(id);
        var response = new ApiResponse.Builder<Place>()
                .success(true)
                .message("Kayıt getirildi.")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find")
    public ResponseEntity<ApiResponse<List<Place>>> searchPlace(@RequestParam String query) {
        var result = placeService.searchByCityOrRegionOrAddress(query);
        var response = new ApiResponse.Builder<List<Place>>()
                .success(true)
                .message("Kayıt getirildi.")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ApiResponse<List<Place>>> findAllApprovedPlaces() {
        var result = placeService.findAllApprovedPlaces();
        var response = new ApiResponse.Builder<List<Place>>()
                .success(true)
                .message("Kayıtlar getirildi.")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/unapprovedPlaces")
    public ResponseEntity<ApiResponse<List<Place>>> findAllUnapprovedPlaces() {
        var result = placeService.findAllUnapprovedPlaces();
        var response = new ApiResponse.Builder<List<Place>>()
                .success(true)
                .message("Kayıtlar getirildi.")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/approve/{id}")
    public ResponseEntity<ApiResponse<Place>> approvePlace(@PathVariable Long id) {
        var result = placeService.approvePlace(id);
        var response = new ApiResponse.Builder<Place>()
                .success(true)
                .message("Onaylandı.")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves places based on the specified zoom level.
     * <p>
     * The zoom level determines which places are included:
     * <ul>
     *   <li>HIGH: Includes all levels (HIGH, MEDIUM, LOW, VERY_LOW).</li>
     *   <li>MEDIUM: Includes MEDIUM, LOW, and VERY_LOW.</li>
     *   <li>LOW: Includes LOW and VERY_LOW.</li>
     *   <li>VERY_LOW: Includes only VERY_LOW.</li>
     * </ul>
     *
     * @param zoomLevel the zoom level to filter places by
     * @return a response containing the list of places matching the zoom level
     */
    @GetMapping("/findByZoomLevel")
    public ResponseEntity<ApiResponse<List<Place>>> findPlacesByZoomLevel(@RequestParam int zoomLevel) {
        ZoomLevel level = ZoomLevel.fromLevel(zoomLevel);
        var result = placeService.findPlacesByZoomLevel(level);
        var response = new ApiResponse.Builder<List<Place>>()
                .success(true)
                .message("Kayıtlar getirildi.")
                .data(result)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}



