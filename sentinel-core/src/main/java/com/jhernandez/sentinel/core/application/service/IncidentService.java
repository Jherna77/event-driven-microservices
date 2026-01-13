package com.jhernandez.sentinel.core.application.service;

import com.jhernandez.sentinel.core.application.dto.CreateIncidentRequest;
import com.jhernandez.sentinel.core.application.dto.CreateIncidentResponse;
import com.jhernandez.sentinel.core.application.port.in.IncidentUseCase;
import com.jhernandez.sentinel.core.application.port.out.DomainEventPublisherPort;
import com.jhernandez.sentinel.core.application.port.out.IncidentRepositoryPort;
import com.jhernandez.sentinel.core.domain.model.entity.Incident;

/**
 * Service class responsible for handling incident-related operations.
 */
public class IncidentService implements IncidentUseCase {

        private final IncidentRepositoryPort incidentRepository;
        private final DomainEventPublisherPort eventPublisher;

        public IncidentService(IncidentRepositoryPort incidentRepository, DomainEventPublisherPort eventPublisher) {
                this.incidentRepository = incidentRepository;
                this.eventPublisher = eventPublisher;
        }

        @Override
        public CreateIncidentResponse reportIncident(CreateIncidentRequest request) {

                Incident incident = Incident.report(
                                request.title(),
                                request.description(),
                                request.severity());

                incidentRepository.saveIncident(incident);

                incident.pullDomainEvents().forEach(eventPublisher::publish);

                return new CreateIncidentResponse(incident.getId());
        }

}
