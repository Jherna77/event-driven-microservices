package com.jhernandez.sentinel.core.domain.model.vo;

import org.junit.jupiter.api.Test;

import com.jhernandez.sentinel.core.domain.exception.ErrorCode;
import com.jhernandez.sentinel.core.domain.exception.IncidentException;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for IncidentTitle value object.
 */
class IncidentTitleTest {

    @Test
    void should_not_allow_empty_title() {

        assertThatThrownBy(() -> new IncidentTitle(""))
                .isInstanceOf(IncidentException.class)
                .hasMessage(ErrorCode.INCIDENT_TITLE_EMPTY.getCode());
    }
}

