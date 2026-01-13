package com.jhernandez.sentinel.core.domain.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jhernandez.sentinel.core.domain.event.IncidentReported;
import com.jhernandez.sentinel.core.domain.exception.ErrorCode;
import com.jhernandez.sentinel.core.domain.exception.IncidentException;
import com.jhernandez.sentinel.core.domain.model.enums.IncidentStatus;
import com.jhernandez.sentinel.core.domain.model.enums.Severity;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentDescription;
import com.jhernandez.sentinel.core.domain.model.vo.IncidentTitle;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for Incident entity.
 */
class IncidentTest {

    Incident incident;

    @BeforeEach
    void setUp() {
        incident = Incident.report(
                new IncidentTitle("Test Incident"),
                new IncidentDescription("This is a test incident"),
                Severity.HIGH);
    }

    @Test
    void should_create_incident_in_open_state() {
        assertThat(incident.getStatus()).isEqualTo(IncidentStatus.OPEN);
        assertThat(incident.getId()).isNotNull();
        assertThat(incident.getReportedAt()).isNotNull();
    }

    @Test
    void should_throw_exception_when_resolving_without_progress() {
        assertThatThrownBy(incident::resolve)
                .isInstanceOf(IncidentException.class)
                .hasMessage(ErrorCode.INCIDENT_INVALID_STATE_FOR_RESOLVE.getCode());
    }

    @Test
    void should_raise_domain_event_when_incident_is_reported() {
        List<IncidentReported> events = incident.pullDomainEvents();
        assertThat(events).hasSize(1);
        assertThat(events.get(0)).isInstanceOf(IncidentReported.class);
    }

    @Test
    void should_move_to_in_progress_when_startProgress() {
        incident.startProgress();
        assertThat(incident.getStatus()).isEqualTo(IncidentStatus.IN_PROGRESS);
    }

    @Test
    void should_resolve_incident_when_in_progress() {
        incident.startProgress();
        incident.resolve();
        assertThat(incident.getStatus()).isEqualTo(IncidentStatus.RESOLVED);
    }

    @Test
    void should_throw_exception_when_startProgress_on_resolved() {
        incident.startProgress();
        incident.resolve();
        assertThatThrownBy(incident::startProgress)
                .isInstanceOf(IncidentException.class)
                .hasMessage(ErrorCode.INCIDENT_INVALID_STATE_FOR_PROGRESS.getCode());
    }

    @Test
    void should_throw_exception_when_resolving_already_resolved() {
        incident.startProgress();
        incident.resolve();
        assertThatThrownBy(incident::resolve)
                .isInstanceOf(IncidentException.class)
                .hasMessage(ErrorCode.INCIDENT_INVALID_STATE_FOR_RESOLVE.getCode());
    }

}
