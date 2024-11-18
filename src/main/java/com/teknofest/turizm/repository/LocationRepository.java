package com.teknofest.turizm.repository;

import com.teknofest.turizm.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByUserId(Long userId);

    Location findByUserIdAndIsCurrentTrue(Long userId);
}
