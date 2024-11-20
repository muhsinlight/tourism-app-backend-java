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
    public Place updatePlace(Long id, Place uPlace) {
        Place dbPlace = getPlaceById(id);
        {
            dbPlace.setName(uPlace.getName());
            dbPlace.setDescription(uPlace.getDescription());
            dbPlace.setAddress(uPlace.getAddress());
            dbPlace.setLatitude(uPlace.getLatitude());
            dbPlace.setLongitude(uPlace.getLongitude());
            dbPlace.setReviews(uPlace.getReviews());
            return placeRepository.save(dbPlace);
        }
    }
    @Override
    public void deletePlace(Long id) {
    Place dbPlace = getPlaceById(id);
    if(dbPlace != null){
        placeRepository.delete(dbPlace);
    }
    throw new IllegalArgumentException("Silinecek id bulanamadı " + id);
    }
    public List<Place> getAllCity() {

    }
    public List<Place> findSearchwithCity(Place place) {

    }

}
