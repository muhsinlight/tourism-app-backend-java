package com.teknofest.turizm.service;

import com.teknofest.turizm.model.Place;

import java.util.List;


public interface PlaceService {
     Place savePlace(Place uplace);
     Place getPlaceById(Long id);
     Place updatePlace(Long id,Place uPlace);
     void  deletePlace(Long id);

     List<Place> searchByCityOrRegionOrAddress(String query);



}
