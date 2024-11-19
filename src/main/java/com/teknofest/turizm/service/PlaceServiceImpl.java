package com.teknofest.turizm.service;

import com.teknofest.turizm.model.Place;
import com.teknofest.turizm.repository.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService{

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
    return placeRepository.findById(id).orElseThrow(null);
    }

    @Override
    public Place updatePlace(Place place) {
        return null;
    }

    @Override
    public void deletePlace(Place place) {

    }


}
