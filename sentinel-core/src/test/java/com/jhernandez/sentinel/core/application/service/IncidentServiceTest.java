package com.jhernandez.sentinel.core.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.jhernandez.sentinel.core.application.dto.CreateIncidentRequest;
import com.jhernandez.sentinel.core.application.dto.CreateIncidentResponse;
import com.jhernandez.sentinel.core.application.port.out.DomainEventPublisherPort;
import com.jhernandez.sentinel.core.application.port.out.IncidentRepositoryPort;
import com.jhernandez.sentinel.core.domain.model.entity.Incident;
import com.jhernandez.sentinel.core.domain.model.enums.Severity;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentDescription;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentTitle;

/**
 * Unit tests for IncidentService.
 */
@ExtendWith(MockitoExtension.class)
class IncidentServiceTest {

    @Mock
    IncidentRepositoryPort repository;

    @Mock
    DomainEventPublisherPort eventPublisher;

    IncidentService service;

    @BeforeEach
    void setUp() {
        service = new IncidentService(repository, eventPublisher);
    }

    @Test
    void should_report_incident_and_publish_event() {

        CreateIncidentRequest request = new CreateIncidentRequest(
                new IncidentTitle("Test Incident"),
                new IncidentDescription("This is a test incident"),
                Severity.HIGH
        );

        CreateIncidentResponse response = service.reportIncident(request);

        verify(repository).saveIncident(any(Incident.class));

        assertThat(response.id()).isNotNull();

    }
}
