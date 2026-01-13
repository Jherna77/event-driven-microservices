package com.jhernandez.sentinel.core.application.port.out;

import com.jhernandez.sentinel.core.domain.model.entity.Incident;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentId;

/**
 * Port interface for incident repository operations.
 */
public interface IncidentRepositoryPort {

    void saveIncident(Incident incident);

    Incident findIncidentById(IncidentId id);

}
