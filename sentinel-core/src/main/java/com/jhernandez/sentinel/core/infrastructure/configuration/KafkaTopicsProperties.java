package com.jhernandez.sentinel.core.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sentinel.kafka.topics")
public class KafkaTopicsProperties {

    private String incidentReported;

}
