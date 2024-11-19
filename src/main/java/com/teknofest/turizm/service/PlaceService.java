package com.teknofest.turizm.service;

import com.teknofest.turizm.model.Place;

public interface PlaceService {
    public Place savePlace(Place place);
    public Place updatePlace(Place place);
    public Place deletePlace(Place place);
    public Place getPlaceById(int id);

}
