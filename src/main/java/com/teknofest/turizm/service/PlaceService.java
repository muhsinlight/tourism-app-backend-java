package com.teknofest.turizm.service;

import com.teknofest.turizm.model.Place;

public interface PlaceService {
     Place savePlace(Place uplace);
     Place getPlaceById(Long id);
     Place updatePlace(Long id,Place place);
     void  deletePlace(Long id);


}
