package com.jhernandez.sentinel.core.domain.exception;

/**
 * Exception class for incident-related errors.
 */
public class IncidentException extends DomainException {

    public IncidentException(ErrorCode errorCode) {
        super(errorCode);
    }

}
