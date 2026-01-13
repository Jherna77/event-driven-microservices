package com.jhernandez.sentinel.core.infrastructure.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.jhernandez.sentinel.core.infrastructure.adapter.out.event.dto.IncidentReportedIntegrationEvent;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, IncidentReportedIntegrationEvent> producerFactory(
            KafkaProperties properties
    ) {
        Map<String, Object> config = new HashMap<>(properties.buildProducerProperties());

        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, IncidentReportedIntegrationEvent> kafkaTemplate(
            ProducerFactory<String, IncidentReportedIntegrationEvent> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }
}


