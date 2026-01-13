package com.jhernandez.sentinel.core.infrastructure.adapter.in.api.controller;

import com.jhernandez.sentinel.core.application.dto.CreateIncidentResponse;
import com.jhernandez.sentinel.core.application.port.in.IncidentUseCase;
import com.jhernandez.sentinel.core.infrastructure.adapter.in.api.dto.CreateIncidentHttpRequest;
import com.jhernandez.sentinel.core.infrastructure.adapter.in.api.mapper.IncidentHttpMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing incidents.
 */
@RestController
@RequestMapping("/incidents")
@RequiredArgsConstructor
@Slf4j
public class IncidentController {

    private final IncidentUseCase incidentUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateIncidentResponse reportIncident(
            @RequestBody CreateIncidentHttpRequest request) {

        log.info("Received request to report {} severity incident {}", request.severity(), request.title());
        return incidentUseCase.reportIncident(IncidentHttpMapper.fromHttp(request));
    }

}
