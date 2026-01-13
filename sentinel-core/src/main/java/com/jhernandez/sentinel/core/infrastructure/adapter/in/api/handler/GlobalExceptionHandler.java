package com.jhernandez.sentinel.core.infrastructure.adapter.in.api.handler;

import java.time.Instant;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jhernandez.sentinel.core.domain.exception.DomainException;
import com.jhernandez.sentinel.core.infrastructure.adapter.in.api.dto.ErrorHttpResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Global exception handler for the application.
 * Handles various custom exceptions and returns localized error messages.
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHttpResponse handleDomainException(DomainException ex, Locale locale) {

        String message = messageSource.getMessage(
                ex.getErrorCode().getCode(),
                null,
                locale);

        log.error("{}: {}", ex.getClass().getSimpleName(), message);

        return new ErrorHttpResponse(
                ex.getErrorCode().getCode(),
                message,
                Instant.now());
    }
}
