package com.jhernandez.sentinel.core.infrastructure.adapter.out.event.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jhernandez.sentinel.core.application.port.out.DomainEventPublisherPort;
import com.jhernandez.sentinel.core.domain.event.IncidentReported;
import com.jhernandez.sentinel.core.infrastructure.adapter.out.event.dto.IncidentReportedIntegrationEvent;
import com.jhernandez.sentinel.core.infrastructure.adapter.out.event.mapper.IncidentEventMapper;
import com.jhernandez.sentinel.core.infrastructure.configuration.KafkaTopicsProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Adapter that implements the DomainEventPublisherPort using Kafka.
 */
@Service
@Profile({"kafka"})
@RequiredArgsConstructor
@Slf4j
public class KafkaDomainEventPublisherAdapter implements DomainEventPublisherPort {

    private final KafkaTemplate<String, IncidentReportedIntegrationEvent> kafkaTemplate;
    private final KafkaTopicsProperties topics;
    @Override
    public void publish(IncidentReported domainEvent) {

        log.info("Publishing IncidentReported event to Kafka for incident ID: {}", domainEvent.title());
        
        IncidentReportedIntegrationEvent integrationEvent = IncidentEventMapper.toIntegrationEvent(domainEvent);
        
        kafkaTemplate.send(topics.getIncidentReported(), integrationEvent);
    }

}
