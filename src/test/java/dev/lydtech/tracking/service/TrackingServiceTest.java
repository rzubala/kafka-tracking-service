package dev.lydtech.tracking.service;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.dispatch.message.TrackingStatusUpdated;
import dev.lydtech.tracking.utils.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackingServiceTest {

    private KafkaTemplate kafkaTemplateMock;

    private TrackingService trackingService;

    @BeforeEach
    void setUp() {
        kafkaTemplateMock = mock(KafkaTemplate.class);
        trackingService = new TrackingService(kafkaTemplateMock);
    }

    @Test
    void sendStatus_Success() throws Exception {
        DispatchPreparing dispatchPreparing = TestEventData.buildDispatchPreparingRandomData();

        when(kafkaTemplateMock.send(anyString(), any(TrackingStatusUpdated.class))).thenReturn(mock(CompletableFuture.class));

        trackingService.sendStatus(dispatchPreparing);

        verify(kafkaTemplateMock, times(1)).send(eq("tracking.status"), any(TrackingStatusUpdated.class));
    }

    @Test
    void sendStatus_ProducerThrowsException() throws Exception {
        DispatchPreparing dispatchPreparing = TestEventData.buildDispatchPreparingRandomData();

        doThrow(new RuntimeException("Service throws exception")).when(kafkaTemplateMock).send(anyString(), any(TrackingStatusUpdated.class));

        Exception exception = assertThrows(RuntimeException.class, () -> trackingService.sendStatus(dispatchPreparing));

        verify(kafkaTemplateMock, times(1)).send(eq("tracking.status"), any(TrackingStatusUpdated.class));

        assertThat(exception.getMessage(), equalTo("Service throws exception"));
    }
}