package com.jhernandez.sentinel.notifier.infrastructure.adapter.in.dto;

import java.time.Instant;

/**
 * DTO representing an incident reported integration event.
 */
public record IncidentReportedIntegrationEvent(
    String incidentId,
    String title,
    String description,
    String severity,
    Instant reportedAt
) {}
