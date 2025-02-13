package com.teknofest.turizm.repository;
import com.teknofest.turizm.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByPlaceId(Long place);
}
