package com.teknofest.turizm.place;

import com.teknofest.turizm.model.Place;
import com.teknofest.turizm.model.ZoomLevel;
import com.teknofest.turizm.repository.PlaceRepository;
import com.teknofest.turizm.service.PlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PlaceServiceTest {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceService placeService;

    @Test
    public void shouldReturnPlacesByHighZoomLevel() {
        List<Place> mockPlaces = MockPlaceData.getMockPlaces();
        placeRepository.saveAll(mockPlaces);

        ZoomLevel zoomLevel = ZoomLevel.HIGH;
        List<Place> places = placeService.findPlacesByZoomLevel(zoomLevel);

        assertThat(places).hasSize(8); // HIGH tüm seviyeleri içerir
    }

    @Test
    public void shouldReturnPlacesByMediumZoomLevel() {
        List<Place> mockPlaces = MockPlaceData.getMockPlaces();
        placeRepository.saveAll(mockPlaces);

        ZoomLevel zoomLevel = ZoomLevel.MEDIUM;
        List<Place> places = placeService.findPlacesByZoomLevel(zoomLevel);

        assertThat(places).hasSize(6); // MEDIUM, LOW ve VERY_LOW içerir
    }

    @Test
    public void shouldReturnPlacesByLowZoomLevel() {
        List<Place> mockPlaces = MockPlaceData.getMockPlaces();
        placeRepository.saveAll(mockPlaces);

        ZoomLevel zoomLevel = ZoomLevel.LOW;
        List<Place> places = placeService.findPlacesByZoomLevel(zoomLevel);

        assertThat(places).hasSize(4); // LOW ve VERY_LOW içerir
    }

    @Test
    public void shouldReturnPlacesByVeryLowZoomLevel() {
        List<Place> mockPlaces = MockPlaceData.getMockPlaces();
        placeRepository.saveAll(mockPlaces);

        ZoomLevel zoomLevel = ZoomLevel.VERY_LOW;
        List<Place> places = placeService.findPlacesByZoomLevel(zoomLevel);

        assertThat(places).hasSize(2); // Yalnızca VERY_LOW içerir
    }
}
