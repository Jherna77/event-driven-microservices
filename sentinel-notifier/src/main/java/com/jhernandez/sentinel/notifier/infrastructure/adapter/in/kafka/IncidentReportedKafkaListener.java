package com.jhernandez.sentinel.notifier.infrastructure.adapter.in.kafka;

import com.jhernandez.sentinel.notifier.application.port.in.IncidentReportedUseCase;
import com.jhernandez.sentinel.notifier.infrastructure.adapter.in.dto.IncidentReportedIntegrationEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka listener for incident reported events.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class IncidentReportedKafkaListener {

    private final IncidentReportedUseCase useCase;

    @KafkaListener(
        topics = "${notifier.kafka.topics.incident-reported}",
        groupId = "${notifier.kafka.group-id}"
    )

    public void onMessage(IncidentReportedIntegrationEvent event) {

        log.info(
            "🔔 NOTIFICATION: Incident [{}] with severity [{}]",
            event.title(),
            event.severity()
        );
        
        useCase.handle(event);
    }
}
