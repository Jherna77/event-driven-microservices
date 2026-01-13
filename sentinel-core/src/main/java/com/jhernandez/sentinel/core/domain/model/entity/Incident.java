package com.jhernandez.sentinel.core.domain.model.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jhernandez.sentinel.core.domain.event.IncidentReported;
import com.jhernandez.sentinel.core.domain.model.enums.IncidentStatus;
import com.jhernandez.sentinel.core.domain.model.enums.Severity;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentDescription;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentId;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentTitle;

/**
 * Represents an incident reported in the system.
 */
public class Incident {

    private final IncidentId id;
    private final IncidentTitle title;
    private final IncidentDescription description;
    private final Severity severity;
    private IncidentStatus status;
    private final Instant reportedAt;
    private final List<IncidentReported> domainEvents = new ArrayList<>();

    private Incident(
            IncidentId id,
            IncidentTitle title,
            IncidentDescription description,
            Severity severity,
            IncidentStatus status,
            Instant reportedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.reportedAt = reportedAt;
    }

     public static Incident report(
            IncidentTitle title,
            IncidentDescription description,
            Severity severity) {
        Incident incident = new Incident(
                new IncidentId(UUID.randomUUID()),
                title,
                description,
                severity,
                IncidentStatus.OPEN,
                Instant.now());
        incident.domainEvents.add(
            new IncidentReported(
                incident.id,
                incident.title,
                incident.description,
                incident.severity,
                incident.reportedAt
            )
        );
        return incident;
    }

    public void startProgress() {
        this.status = status.startProgress();
    }

    public void resolve() {
        this.status = status.resolve();
    }

    public IncidentId getId() {
        return id;
    }

    public IncidentTitle getTitle() {
        return title;
    }

    public IncidentDescription getDescription() {
        return description;
    }

    public Severity getSeverity() {
        return severity;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public Instant getReportedAt() {
        return reportedAt;
    }

    public List<IncidentReported> pullDomainEvents() {
        List<IncidentReported> events = List.copyOf(domainEvents);
        domainEvents.clear();
        return events;
    }
}
