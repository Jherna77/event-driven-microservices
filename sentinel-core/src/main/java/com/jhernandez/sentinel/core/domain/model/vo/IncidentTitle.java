package com.jhernandez.sentinel.core.domain.model.vo;

import com.jhernandez.sentinel.core.domain.exception.ErrorCode;
import com.jhernandez.sentinel.core.domain.exception.IncidentException;

/**
 * Value Object representing the title of an incident.
 */
public record IncidentTitle(String title) {

    public IncidentTitle {
        if (title == null || title.isBlank()) {
            throw new IncidentException(ErrorCode.INCIDENT_TITLE_EMPTY);
        }
        if (title.length() > 100) {
            throw new IncidentException(ErrorCode.INCIDENT_TITLE_TOO_LONG);
        }
    }
}
