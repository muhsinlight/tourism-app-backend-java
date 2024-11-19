package com.teknofest.turizm.repository;

import com.teknofest.turizm.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
