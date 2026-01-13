package com.jhernandez.sentinel.core.infrastructure.adapter.out.event.dto;

import java.time.Instant;

/**
 * Integration event representing an incident being reported.
 */
public record IncidentReportedIntegrationEvent(
        String incidentId,
        String title,
        String description,
        String severity,
        Instant reportedAt
) {}