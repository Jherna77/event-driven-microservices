package com.jhernandez.sentinel.core.domain.exception;

/**
 * Enumeration of error codes used in the Sentinel domain.
 */
public enum ErrorCode {
    INCIDENT_INVALID_ID("incident.invalid.id"),
    INCIDENT_TITLE_EMPTY("incident.title.empty"),
    INCIDENT_TITLE_TOO_LONG("incident.title.too.long"),
    INCIDENT_DESCRIPTION_EMPTY("incident.description.empty"),
    INCIDENT_DESCRIPTION_TOO_LONG("incident.description.too.long"),
    INCIDENT_INVALID_STATE_FOR_PROGRESS("incident.invalid.state.for.progress"),
    INCIDENT_INVALID_STATE_FOR_RESOLVE("incident.invalid.state.for.resolve"),
    INCIDENT_INVALID_SEVERITY("incident.invalid.severity");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
