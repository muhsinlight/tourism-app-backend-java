package com.teknofest.turizm.repository;

import com.teknofest.turizm.model.DatabaseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseLogRepository extends JpaRepository<DatabaseLog, Long> {
}
