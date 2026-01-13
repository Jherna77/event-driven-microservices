package com.jhernandez.sentinel.core.infrastructure.adapter.out.event.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.jhernandez.sentinel.core.infrastructure.adapter.out.event.dto.IncidentReportedIntegrationEvent;

@SpringBootTest
@ActiveProfiles("kafka")
@EmbeddedKafka(partitions = 1, topics = { "${sentinel.kafka.topics.incident-reported}" })
@TestPropertySource(properties = {
                "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
class IncidentKafkaIntegrationTest {

        @Autowired
        private KafkaTemplate<String, IncidentReportedIntegrationEvent> kafkaTemplate;

        @Autowired
        private EmbeddedKafkaBroker embeddedKafkaBroker;

        @Value("${sentinel.kafka.topics.incident-reported}")
        private String topicName;

        @Autowired
        private ConsumerFactory<String, IncidentReportedIntegrationEvent> consumerFactory;

        private Consumer<String, IncidentReportedIntegrationEvent> testConsumer;

        @BeforeEach
        void setUp() {
                testConsumer = consumerFactory.createConsumer();
                embeddedKafkaBroker.consumeFromAnEmbeddedTopic(testConsumer, topicName);
        }

        @AfterEach
        void tearDown() {
                if (testConsumer != null) {
                        testConsumer.close();
                }
        }

        @Test
        void should_publish_and_receive_incident_reported_event() {
                // GIVEN
                IncidentReportedIntegrationEvent event = new IncidentReportedIntegrationEvent(
                                "123", "Test title", "Test description", "HIGH", Instant.now());

                // WHEN
                kafkaTemplate.send(topicName, "123", event);

                // THEN
                ConsumerRecord<String, IncidentReportedIntegrationEvent> received = KafkaTestUtils
                                .getSingleRecord(testConsumer, topicName, Duration.ofSeconds(5));

                assertThat(received.key()).isEqualTo("123");
                assertThat(received.value().incidentId()).isEqualTo("123");
        }
}