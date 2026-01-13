package com.jhernandez.sentinel.notifier.application.service;

import com.jhernandez.sentinel.notifier.application.port.in.IncidentReportedUseCase;
import com.jhernandez.sentinel.notifier.infrastructure.adapter.in.dto.IncidentReportedIntegrationEvent;

/**
 * Service implementation for handling incident reported events.
 */
public class IncidentReportedService implements IncidentReportedUseCase {

    @Override
    public void handle(IncidentReportedIntegrationEvent event) {
        // Implementation logic for handling the incident reported event
        System.out.println("Handling incident reported event: " + event);
    }
}
