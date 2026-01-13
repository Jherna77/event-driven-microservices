package com.jhernandez.sentinel.core.application.port.out;

import com.jhernandez.sentinel.core.domain.event.IncidentReported;

/**
 * Port for publishing domain events.
 */
public interface DomainEventPublisherPort {

    void publish(IncidentReported event);
    
}

