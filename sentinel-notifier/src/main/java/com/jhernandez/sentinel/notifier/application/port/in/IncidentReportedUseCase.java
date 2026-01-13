package com.jhernandez.sentinel.notifier.application.port.in;

import com.jhernandez.sentinel.notifier.infrastructure.adapter.in.dto.IncidentReportedIntegrationEvent;

/**
 * Use case interface for handling incident reported events.
 */
public interface IncidentReportedUseCase {

    void handle(IncidentReportedIntegrationEvent event);

}
