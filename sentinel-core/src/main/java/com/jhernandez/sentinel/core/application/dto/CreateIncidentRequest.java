package com.jhernandez.sentinel.core.application.dto;

import com.jhernandez.sentinel.core.domain.model.enums.Severity;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentDescription;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentTitle;

/**
 * DTO for creating a new incident.
 */
public record CreateIncidentRequest(
        IncidentTitle title,
        IncidentDescription description,
        Severity severity
) {}
