package com.teknofest.turizm.service;

import com.teknofest.turizm.exception.ResourceNotFoundException;
import com.teknofest.turizm.model.Place;
import com.teknofest.turizm.model.ZoomLevel;
import com.teknofest.turizm.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public Place getPlaceById(Long id) {
        return placeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kayıt bulunamadı."));
    }

    @Override
    public Place updatePlace(Long id, Place uPlace) {
        Place dbPlace = placeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID:" + id + " ile eşleşen kayıt bulunamadı."));
        {
            dbPlace.setName(uPlace.getName());
            dbPlace.setDescription(uPlace.getDescription());
            dbPlace.setAddress(uPlace.getAddress());
            dbPlace.setLatitude(uPlace.getLatitude());
            dbPlace.setLongitude(uPlace.getLongitude());

            return placeRepository.save(dbPlace);
        }
    }

    @Override
    public void deletePlace(Long id) {
        Optional<Place> placeDb = placeRepository.findById(id);
        if (placeDb.isPresent()) {
            placeRepository.delete(placeDb.get());
        } else {
            throw new ResourceNotFoundException("ID:" + id + " ile eşleşen kayıt bulunamadı.");
        }
    }

    @Override
    public List<Place> searchByCityOrRegionOrAddress(String query) {
        return placeRepository.searchByCityOrRegionOrAddress(query);
    }

    @Override
    public List<Place> findAllApprovedPlaces() {
       return placeRepository.findAllApprovedPlaces();
    }

    @Override
    public List<Place> findAllUnapprovedPlaces() {
        return placeRepository.findAllUnapprovedPlaces();
    }

    @Override
    public boolean approvePlace(Long id) {
        int result = placeRepository.approvePlace(id);
        if (result < 0) {
            throw new RuntimeException("İlgili yer bulunamadı veya zaten onaylanmış.");
        }
        return true;
    }

    /**
     * Finds places based on the specified zoom level.
     * <p>
     * This method applies a hierarchical logic to determine which places to include:
     * <ul>
     *   <li>HIGH: Includes HIGH, MEDIUM, LOW, and VERY_LOW level places.</li>
     *   <li>MEDIUM: Includes MEDIUM, LOW, and VERY_LOW level places.</li>
     *   <li>LOW: Includes LOW and VERY_LOW level places.</li>
     *   <li>VERY_LOW: Includes only VERY_LOW level places.</li>
     * </ul>
     *
     * @param zoomLevel the zoom level to filter places by
     * @return a list of places matching the specified zoom level and its hierarchy
     */
    public List<Place> findPlacesByZoomLevel(ZoomLevel zoomLevel) {
        List<ZoomLevel> levelsToInclude = getZoomLevelsToInclude(zoomLevel);
        return placeRepository.findAll().stream()
                .filter(place -> levelsToInclude.contains(place.getZoomLevel()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the hierarchical ZoomLevel list
     * @param zoomLevel
     * @return the hierarchical ZoomLevel list
     */
    private List<ZoomLevel> getZoomLevelsToInclude(ZoomLevel zoomLevel) {
        return switch (zoomLevel) {
            case HIGH -> Arrays.asList(ZoomLevel.HIGH, ZoomLevel.MEDIUM, ZoomLevel.LOW, ZoomLevel.VERY_LOW);
            case MEDIUM -> Arrays.asList(ZoomLevel.MEDIUM, ZoomLevel.LOW, ZoomLevel.VERY_LOW);
            case LOW -> Arrays.asList(ZoomLevel.LOW, ZoomLevel.VERY_LOW);
            default -> Collections.singletonList(ZoomLevel.VERY_LOW);
        };
    }
}
