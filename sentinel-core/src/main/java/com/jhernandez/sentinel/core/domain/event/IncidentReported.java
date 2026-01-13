package com.jhernandez.sentinel.core.domain.event;

import java.time.Instant;

import com.jhernandez.sentinel.core.domain.model.enums.Severity;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentDescription;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentId;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentTitle;

/**
 * Event representing that an incident has been reported.
 */
public record IncidentReported(
        IncidentId incidentId,
        IncidentTitle title,
        IncidentDescription description,
        Severity severity,
        Instant reportedAt
) {
}

