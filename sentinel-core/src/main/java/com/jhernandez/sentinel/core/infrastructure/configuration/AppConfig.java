package com.jhernandez.sentinel.core.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhernandez.sentinel.core.application.port.in.IncidentUseCase;
import com.jhernandez.sentinel.core.application.port.out.DomainEventPublisherPort;
import com.jhernandez.sentinel.core.application.port.out.IncidentRepositoryPort;
import com.jhernandez.sentinel.core.application.service.IncidentService;

/**
 * Application configuration class that defines beans for the application context.
 */
@Configuration
public class AppConfig {

    @Bean
    public IncidentUseCase incidentUseCase(
        IncidentRepositoryPort incidentRepository,
        DomainEventPublisherPort eventPublisher) {
        return new IncidentService(incidentRepository, eventPublisher);
    }
}
