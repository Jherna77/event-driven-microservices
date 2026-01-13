package com.jhernandez.sentinel.core.infrastructure.adapter.in.api.dto;

/**
 * DTO for creating an incident via HTTP request.
 */
public record CreateIncidentHttpRequest(
        String title,
        String description,
        String severity
) {}

