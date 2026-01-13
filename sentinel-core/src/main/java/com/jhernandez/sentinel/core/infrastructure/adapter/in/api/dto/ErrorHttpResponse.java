package com.jhernandez.sentinel.core.infrastructure.adapter.in.api.dto;

import java.time.Instant;

public record ErrorHttpResponse(
        String code,
        String message,
        Instant timestamp
) {}

