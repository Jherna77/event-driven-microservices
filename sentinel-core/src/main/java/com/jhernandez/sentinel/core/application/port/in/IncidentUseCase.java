package com.jhernandez.sentinel.core.application.port.in;

import com.jhernandez.sentinel.core.application.dto.CreateIncidentRequest;
import com.jhernandez.sentinel.core.application.dto.CreateIncidentResponse;

/**
 * Use case interface for incident-related operations.
 */
public interface IncidentUseCase {

    CreateIncidentResponse reportIncident(CreateIncidentRequest request);

}
