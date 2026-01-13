package com.jhernandez.sentinel.core.application.dto;

import com.jhernandez.sentinel.core.domain.model.vo.IncidentId;

/**
 * DTO for the response after creating a new incident.
 */
public record CreateIncidentResponse(IncidentId id) {}
