package com.jhernandez.sentinel.core.domain.model.enums;

import com.jhernandez.sentinel.core.domain.exception.ErrorCode;
import com.jhernandez.sentinel.core.domain.exception.IncidentException;

/**
 * Enumeration representing the status of an incident.
 */
public enum IncidentStatus {

    OPEN {
        @Override
        public IncidentStatus startProgress() {
            return IN_PROGRESS;
        }
    },
    IN_PROGRESS {
        @Override
        public IncidentStatus resolve() {
            return RESOLVED;
        }
    },
    RESOLVED;

    public IncidentStatus startProgress() {
        throw new IncidentException(ErrorCode.INCIDENT_INVALID_STATE_FOR_PROGRESS);
    }

    public IncidentStatus resolve() {
        throw new IncidentException(ErrorCode.INCIDENT_INVALID_STATE_FOR_RESOLVE);
    }
}

