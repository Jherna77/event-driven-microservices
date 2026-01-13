package com.jhernandez.sentinel.notifier.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhernandez.sentinel.notifier.application.port.in.IncidentReportedUseCase;
import com.jhernandez.sentinel.notifier.application.service.IncidentReportedService;

/**
 * Application configuration class for defining beans.
 */
@Configuration
public class AppConfig {

    @Bean
    public IncidentReportedUseCase incidentReportedUseCase() {
        return new IncidentReportedService();
    }
}
