package com.jhernandez.sentinel.core.infrastructure.adapter.out.event.mapper;

import com.jhernandez.sentinel.core.domain.event.IncidentReported;
import com.jhernandez.sentinel.core.infrastructure.adapter.out.event.dto.IncidentReportedIntegrationEvent;

/**
 * Mapper class to convert domain events to integration events.
 */
public class IncidentEventMapper {

    public static IncidentReportedIntegrationEvent toIntegrationEvent(IncidentReported event) {

        return new IncidentReportedIntegrationEvent(
                event.incidentId().id().toString(),
                event.title().title(),
                event.description().description(),
                event.severity().name(),
                event.reportedAt()
        );
    }
}

