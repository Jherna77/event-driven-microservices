package com.jhernandez.sentinel.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.jhernandez.sentinel.core.application.port.out.DomainEventPublisherPort;
import com.jhernandez.sentinel.core.application.port.out.IncidentRepositoryPort;

@SpringBootTest
@ActiveProfiles("test")
class SentinelCoreApplicationTests {

	@MockitoBean 
    private DomainEventPublisherPort domainEventPublisherPort;

    @MockitoBean 
    private IncidentRepositoryPort incidentRepositoryPort;
	
	@Test
	void contextLoads() {
	}

}
