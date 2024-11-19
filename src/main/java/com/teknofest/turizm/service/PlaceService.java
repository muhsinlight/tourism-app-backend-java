package com.teknofest.turizm.service;

import com.teknofest.turizm.model.Place;

public interface PlaceService {
     Place savePlace(Place place);
     Place updatePlace(Place place);
     void  deletePlace(Place place);
     Place getPlaceById(Long id);

}
