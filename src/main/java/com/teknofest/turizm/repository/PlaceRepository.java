package com.teknofest.turizm.repository;

import com.teknofest.turizm.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("")
List<Place> findSearchwithCity(Place place);
}
