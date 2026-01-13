package com.jhernandez.sentinel.core.domain.model.vo;

import com.jhernandez.sentinel.core.domain.exception.ErrorCode;
import com.jhernandez.sentinel.core.domain.exception.IncidentException;

/**
 * Value Object representing the description of an incident.
 */
public record IncidentDescription(String description) {
    public IncidentDescription {
        if (description == null || description.isBlank()) {
            throw new IncidentException(ErrorCode.INCIDENT_DESCRIPTION_EMPTY);
        }
        if (description.length() > 500) {
            throw new IncidentException(ErrorCode.INCIDENT_DESCRIPTION_TOO_LONG);
        }
    }
    

}
