package com.teknofest.turizm.service;

import com.teknofest.turizm.model.DatabaseLog;
import com.teknofest.turizm.repository.DatabaseLogRepository;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    private final DatabaseLogRepository databaseLogRepository;

    public LoggingService(DatabaseLogRepository databaseLogRepository) {
        this.databaseLogRepository = databaseLogRepository;
    }

    public void saveLog(String message, String methodName) {
        DatabaseLog log = new DatabaseLog();
        log.setMessage(message);
        log.setMethodName(methodName);
        log.setTimestamp(String.valueOf(System.currentTimeMillis()));
        databaseLogRepository.save(log);
    }
}
