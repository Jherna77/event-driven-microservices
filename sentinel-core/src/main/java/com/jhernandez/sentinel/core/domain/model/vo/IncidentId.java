package com.jhernandez.sentinel.core.domain.model.vo;

import java.util.UUID;

import com.jhernandez.sentinel.core.domain.exception.ErrorCode;
import com.jhernandez.sentinel.core.domain.exception.IncidentException;

/**
 * Value Object representing the unique identifier of an incident.
 */
public record IncidentId(UUID id) {

    public IncidentId {
        if (id == null) {
            throw new IncidentException(ErrorCode.INCIDENT_INVALID_ID);
        }
    }
}
