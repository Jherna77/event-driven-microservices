package com.jhernandez.sentinel.core.infrastructure.adapter.out.persistence;

import com.jhernandez.sentinel.core.application.port.out.IncidentRepositoryPort;
import com.jhernandez.sentinel.core.domain.model.entity.Incident;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * In-memory implementation of the IncidentRepository for demonstration and testing purposes.
 * This class uses a ConcurrentHashMap to store incidents in memory.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InMemoryIncidentRepositoryAdapter implements IncidentRepositoryPort {

    private final Map<IncidentId, Incident> storage = new ConcurrentHashMap<>();

    @Override
    public void saveIncident(Incident incident) {
        log.info("Saving incident with ID: {}", incident.getId());
        storage.put(incident.getId(), incident);
    }

    @Override
    public Incident findIncidentById(IncidentId incidentId) {
        log.info("Finding incident with ID: {}", incidentId);
        return storage.get(incidentId);
    }
}
