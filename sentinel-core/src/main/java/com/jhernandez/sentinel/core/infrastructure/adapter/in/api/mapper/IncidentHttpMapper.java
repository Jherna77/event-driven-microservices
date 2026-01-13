package com.jhernandez.sentinel.core.infrastructure.adapter.in.api.mapper;

import com.jhernandez.sentinel.core.application.dto.CreateIncidentRequest;
import com.jhernandez.sentinel.core.domain.exception.ErrorCode;
import com.jhernandez.sentinel.core.domain.exception.IncidentException;
import com.jhernandez.sentinel.core.domain.model.enums.Severity;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentDescription;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentTitle;
import com.jhernandez.sentinel.core.infrastructure.adapter.in.api.dto.CreateIncidentHttpRequest;

/**
 * Mapper for Incident HTTP DTOs.
 */
public class IncidentHttpMapper {

    public static CreateIncidentRequest fromHttp(CreateIncidentHttpRequest request) {
        return new CreateIncidentRequest(
                new IncidentTitle(request.title()),
                new IncidentDescription(request.description()),
                mapSeverity(request.severity()));
    }

    private static Severity mapSeverity(String raw) {
        try {
            return Severity.valueOf(raw.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IncidentException(ErrorCode.INCIDENT_INVALID_SEVERITY);
        }
    }
}